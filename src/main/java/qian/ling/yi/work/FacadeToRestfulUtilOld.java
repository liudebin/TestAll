package qian.ling.yi.work;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * FacadeToRestfulUtil
 *
 * @date: 2018/12/4.
 * @author: guobin.liu@holaverse.com
 */

public class FacadeToRestfulUtilOld {

    static public List<String> priType = Arrays.asList("int", "char", "double", "float"
            , "String", "Integer", "Double", "Float");
    private static String feignClient;

    /**
     * NIO读取百万级别文件
     *
     * @author Chillax
     */
    public static void main(String args[]) throws Exception {
//        File file = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java");
//        readLine(file).ifPresent(s->writeLine(s, file));

//        String path = "D:/ideaProjects/rds";
//        String path = "D:/ideaProjects/mes";
        String path = "D:/ideaProjects/ces";

//        setFeignClient("rds-app", "rds");
//        setFeignClient("mes-app", "mes");
        setFeignClient("ces-app", "ces");
        getFacadeOrImplList(path, "Facade")
                .forEach(file -> readLine(file, FacadeToRestfulUtilOld::refactorFacadeLine).ifPresent(s -> writeLine(s, file)));
//        File file = new File("D:\\ideaProjects\\rds\\rds-app\\src\\main\\java\\com\\qihoo\\finance\\rds\\modules\\blacklist\\facade\\BlackListFacadeImpl.java");
//        readLine(file, FacadeToRestfulUtil::refactorFacadeImplLine)
//                .ifPresent(s -> writeLine(s,file ));


        getFacadeOrImplList(path, "FacadeImpl")
                .forEach(file -> readLine(file, FacadeToRestfulUtilOld::refactorFacadeImplLine)
                        .ifPresent(s -> writeLine(s, file)));


    }

    private static void testMappedByteBuffer(FileChannel channel, long size) throws IOException {
        // 构建一个只读的MappedByteBuffer
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

        // 如果文件不大,可以选择一次性读取到数组
        byte[] all = new byte[(int) size];
        mappedByteBuffer.get(all, 0, (int) size);
////         打印文件内容
//         System.out.println(new String(all));
        //鸟枪
    }

    public static Optional<List<String>> readLine(File file, Function<String, String> refactorLineFunction) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return Optional.of(bufferedReader.lines().map(refactorLineFunction).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void writeLine(List<String> lines, File file) {
        File fOut = new File(file.getAbsolutePath() + ".tmp");
        try (FileWriter fileWriter = new FileWriter(fOut);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            lines.forEach(str -> Optional.ofNullable(str).ifPresent(s -> bufferWriteLine(bufferedWriter, str)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
        fOut.renameTo(file);

    }

    private static void bufferWriteLine(BufferedWriter bufferedWriter, String str) {
        try {
            bufferedWriter.write(str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String refactorFacadeImplLine(String line) {
        return Optional.of(line)
                .filter(l -> !l.contains("@Service"))
                .map(l -> facadeImplAddRestAnnotation(l)
                        .orElseGet(() ->
                                implAddPostMapping(line)
                                        .orElseGet(() -> getImplImport(line)))
                ).orElse(null);
    }

    private static String refactorFacadeLine(String line) {
        return facadeInterfaceAddFeignOption(line)
                .orElseGet(() ->
                        addPostMapping(line)
                                .orElseGet(() -> getImport(line)));

    }

    public static String getImport(String line) {

        return Optional.of(line)
                .filter(l -> l.contains("package"))
                .map(l -> l.concat("\n import org.springframework.web.bind.annotation.PostMapping;")
                        .concat("\n")
                        .concat("import org.springframework.cloud.openfeign.FeignClient;")
                        .concat("\n")
                        .concat("import org.springframework.web.bind.annotation.RequestMapping;")
                        .concat("\n")
                        .concat("import org.springframework.web.bind.annotation.RequestParam;")
                        .concat("\n")
                        .concat("import org.springframework.web.bind.annotation.RequestBody;")
                        .concat("\n"))
                .orElse(line);
    }

    public static String getImplImport(String line) {

        return Optional.of(line)
                .filter(l -> l.contains("package"))
                .map(l -> l.concat("\n import org.springframework.web.bind.annotation.RestController;")
                        .concat("\n")
                        .concat("import org.springframework.web.bind.annotation.RequestParam;")
                        .concat("\n")
                        .concat("import org.springframework.web.bind.annotation.RequestBody;")
                        .concat("\n"))
                .orElse(line);
    }


    public static List<File> getFacadeOrImplList(String path, String keyWord) {
        List<File> facadeOrImpl = new ArrayList<>();

        Arrays.stream(Objects.requireNonNull(new File(path).listFiles()))
                .filter(File::isDirectory)
                .forEach(file2 -> {
                            collectFacadeOrImplFromDir(file2, keyWord).ifPresent(facadeOrImpl::addAll);
                            facadeOrImpl.addAll(getFacadeOrImplList(file2.getAbsolutePath(), keyWord));
                        }
                );

        return facadeOrImpl;
    }


    public static List<File> getFile(File dir, String keyWord) {
        return  Optional.of(dir)
                .map(File::listFiles)
                .map(Arrays::stream)
                .map(stream -> stream
                        .filter(f ->
                                f.getName().endsWith(keyWord + ".java")
                                        && !f.getName().contains("Ext")
                        )
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }


    private static Optional<List<File>> collectFacadeOrImplFromDir(File file, String key) {
        return Optional.of(file).filter(n -> n.exists() && n.isDirectory())
                .map(n -> Arrays.asList(Objects.requireNonNull(n.listFiles(f -> f.getName().endsWith(key + ".java")
                        && !f.getName().contains("Ext")))));

    }


    static Optional<String> addRequestAnnotation(String str) {
        return Optional.of(str.substring(0, str.indexOf("("))
                .trim()
                .split(" "))
                .filter(n -> n.length > 1).flatMap(n -> Arrays.stream(n).skip(n.length - 1)
                        .map(m -> "\t@PostMapping(\"/" + m + "\")").findFirst());

    }


    static Optional<String> addPostMapping(String line) {
        return Optional.of(line).filter(l -> l.contains("("))
                .map(str -> str.substring(0, str.indexOf("("))
                        .trim()
                        .split(" "))
                .filter(n -> n.length > 1).flatMap(n -> Arrays.stream(n).skip(n.length - 1)
                        .map(m -> "\t@PostMapping(\"/" + m + "\")\n")
                        .findFirst())
                .map(l -> l.concat(addRequestTypeStr(line)));

    }

    static Optional<String> implAddPostMapping(String line) {
        return Optional.of(line)
                .filter(l -> l.contains("(") && l.contains("public"))
                .map(l -> addImplRequestType(l).get());
    }

    static Optional<String> addRequestType(String str) {
//        String bizTpye, String bizNo
        String[] split =
                str.substring(str.indexOf("(") + 1, str.indexOf(")"))
                        .trim()
                        .split(",");

        Map<String, String> map = new HashMap<>();
        Arrays.stream(split).forEach(n -> {
            String[] s = n.trim().split(" ");
            if (s.length == 2) {
                if (startWithPrimate(s[0])) {
                    map.put(n, "@RequestParam(\"".concat(s[1]).concat("\")").concat(n));
                } else {
                    map.put(n, "@RequestBody ".concat(n));
                }
            }
        });

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            str = str.replaceAll(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return Optional.of(str);
    }

    static String addRequestTypeStr(String str) {
//        String bizTpye, String bizNo
        String[] split =
                str.substring(str.indexOf("(") + 1, str.indexOf(")"))
                        .trim()
                        .split(",");

        Map<String, String> map = new HashMap<>();
        Arrays.stream(split).forEach(n -> {
            String[] s = n.trim().split(" ");
            if (s.length == 2) {
                if (startWithPrimate(s[0])) {
                    map.put(n, "@RequestParam(\"".concat(s[1]).concat("\")").concat(n));
                } else {
                    map.put(n, "@RequestBody ".concat(n));
                }
            }
        });

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            str = str.replaceAll(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return str;
    }

    static Optional<String> addImplRequestType(String str) {
//        String bizTpye, String bizNo
        String[] split =
                str.substring(str.indexOf("(") + 1, str.indexOf(")"))
                        .trim()
                        .split(",");

        Map<String, String> map = new HashMap<>();
        Arrays.stream(split).forEach(n -> {
            String[] s = n.trim().split(" ");
            if (s.length == 2) {
                if (startWithPrimate(s[0])) {
                    map.put(n, n);
                } else {
                    map.put(n, "@RequestBody ".concat(n));
                }
            }
        });

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            str = str.replaceAll(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return Optional.of(str);
    }


    static boolean startWithPrimate(String str) {
        return priType.stream().anyMatch(str::startsWith);
    }


    static Optional<String> addRestAnnotation(String str) {
        return Optional.of("@RestController");
    }

    static Optional<String> interfaceAddFeignAndRequestMapping(String str) {
        String sb = getFeignClient() + "@RequestMapping(\"/" +
                str.substring(str.indexOf(" interface") + 10, str.indexOf("{")).trim() + "\")";
        return Optional.of(sb);
    }

    static String facadeInterfaceAddFeign(String line) {
        return getFeignClient()
                .concat("@RequestMapping(\"/")
                .concat(line.substring(line.indexOf(" interface") + 10, line.indexOf("{")).trim() + "\")")
                .concat("\n").concat(line);
    }

    private static String getFeignClient() {
        return feignClient;
    }

    private static void setFeignClient(String app, String domain) {
        feignClient = "@FeignClient(name = \"" + app + "\", url = \"http://" + domain + "${dnsDomain:}\")\n";
    }


    static Optional<String> facadeInterfaceAddFeignOption(String line) {
        return Optional.of(line).filter(l -> l.contains("public interface"))
                .map(FacadeToRestfulUtilOld::facadeInterfaceAddFeign);
    }


    private static Optional<String> facadeImplAddRestAnnotation(String line) {
        return Optional.of(line)
                .filter(l -> l.contains("public class"))
                .map(l -> "@RestController".concat("\n ").concat(l));
    }


    static void printHasServiceAnnotation(String line, String name) {
        line = line.trim();
        if (line.contains("@Service")) {
            System.out.println(name);
        }
    }

}
