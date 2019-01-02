package qian.ling.yi.work;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * FacadeToRestfulUtil
 *
 * @date: 2018/12/4.
 * @author: guobin.liu@holaverse.com
 */

public class DubboToFeignUtil {

    static Map<String, String> versions = new HashMap<String, String>() {{
        put("<ops-api.version", "1.38.3-SNAPSHOT");
        put("<apv-api.version", "1.37.3-SNAPSHOT");
        put("<cas-api.version", "1.16.2-SNAPSHOT");
        put("<crs-api.version", "1.18.1-SNAPSHOT");
        put("<ris-api.version", "1.12.2-SNAPSHOT");
        put("<lcs-api.version", "2.21.1-SNAPSHOT");
        put("<ams-api.version", "2.6.3-SNAPSHOT");
        put("<fas-api.version", "2.1.4-SNAPSHOT");
        put("<mms-api.version", "3.3.2-SNAPSHOT");
        put("<gws-api.version", "1.37.7-SNAPSHOT");
        put("<lps-api.version", "3.5.3-SNAPSHOT");
        put("<gns-api.version", "1.8.3-SNAPSHOT");
        put("<rds-api.version", "1.34.1-SNAPSHOT");
        put("<rfs-api.version", "1.17.1-SNAPSHOT");
        put("<mes-api.version", "1.0.8-SNAPSHOT");
        put("<ces-api.version", "1.7.2-SNAPSHOT");
        put("<dcs-api.version", "1.30.0-SNAPSHOT");
        put("<com-api.version", "1.1.3-SNAPSHOT");
        put("<cis-api.version", "2.1.2-SNAPSHOT");
        put("<common-api.version", "1.5.0-SNAPSHOT");
    }};

    static List<String> priType = Arrays.asList("int", "char", "double", "float"
            , "String", "Integer", "Double", "Float");
    private static String feignClient;

    /**
     * NIO读取百万级别文件
     *
     * @author Chillax
     */
    public static void main(String args[]) throws Exception {
//        String path = "D:/ideaProjects/ces";
//        String path = "D:/ideaProjects/com";
        String path = "D:/ideaProjects/rds";

//        setFeignClient("com-app", "com");
//        getFileByType(path, "Facade.java")
//                .forEach(file -> readLine(file, DubboToFeignUtil::refactorFacadeLine)
//                        .ifPresent(s -> writeLine(s, file)));
//
//        getFileByType(path, "FacadeImpl.java")
//                .forEach(file -> readLine(file, DubboToFeignUtil::refactorFacadeImplLine)
//                        .ifPresent(s -> writeLine(s, file)));
        getFileByType(path, "Facade.java")
                .forEach(file -> readLine(file, DubboToFeignUtil::response)
                        .ifPresent(n->n.stream().filter(Objects::nonNull).forEach(System.out::println)));


    }



    public static Optional<List<String>> readLine(File file, Function<String, String> function) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return Optional.of(bufferedReader.lines().map(function).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void writeLine(List<String> lines, File file) {
        File fOut = new File(file.getAbsolutePath() + ".tmp");
        try (FileWriter fileWriter = new FileWriter(fOut);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            lines.forEach(
                    str -> Optional.ofNullable(str)
                            .ifPresent(s -> bufferWriteLine(bufferedWriter, str))
            );
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

    private static String response(String line) {
        return Optional.of(line)
                .filter(l -> l.contains("Response") &&!l.contains("Response<") &&!l.contains("import"))
               .orElse(null);
    }


    public static String filterApi(String line) {
        versions.entrySet().stream().filter(n-> line.contains(n.getKey())).findAny().ifPresent(n -> {
            System.out.println(line + "  "+n.getValue() + " " + "\"com.qihoo.finance."+ n.getKey().substring(1, 4) + "\",");
//            System.out.println("\"com.qihoo.finance."+ n.getKey().substring(1, 4) + "\",");
        });
        return "";
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


    public static List<File> getDir(String path) {
        return Arrays.stream(Objects.requireNonNull(new File(path).listFiles()))
                .filter(f -> f.isDirectory() && !f.getName().contains("."))
                .map(f -> {
                            List<File> list = getDir(f.getAbsolutePath());
                            list.add(f);
                            return list;
                        }
                ).reduce((n, n1) -> {
                    n.addAll(n1);
                    return n;
                }).orElse(new ArrayList<>());

    }

    public static List<File> getFile(File dir, String keyWord) {
        return Optional.of(dir)
                .map(File::listFiles)
                .map(Arrays::stream)
                .map(stream -> stream
                        .filter(f ->
                                f.getName().endsWith(keyWord)
                                        && !f.getName().contains("Ext")
                        )
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

    public static List<File> getFileByType(String path, String keyWord) {
        return Stream.of(getDir(path), Collections.singletonList(new File(path)))
                .flatMap(Collection::stream)
                .flatMap(n -> getFile(n, keyWord).stream())
                .collect(Collectors.toList());
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
                .map(DubboToFeignUtil::addImplRequestType);
    }

    static String addRequestTypeStr(String str) {
//        (String bizTpye, String bizNo)

        Map<String, String> map = Arrays.stream(
                str.substring(str.indexOf("(") + 1, str.indexOf(")"))
                        .trim()
                        .split(","))
                .filter(n -> n.trim().split(" ").length == 2)
                .collect(Collectors.toMap(n -> n, DubboToFeignUtil::refactorFacadeParam));


        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            str = str.replaceAll(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return str;
    }

    private static String refactorFacadeParam(String n) {
        String[] s = n.trim().split(" ");
        String str1;
        if (startWithPrimate(s[0])) {
            str1 = "@RequestParam(\"".concat(s[1]).concat("\")").concat(n);
        } else {
            str1 = "@RequestBody ".concat(n);
        }
        return str1;
    }

    public static String addImplRequestType(String str) {
//        (String bizTpye, String bizNo)

        Map<String, String> map = Arrays.stream(
                str.substring(str.indexOf("(") + 1, str.indexOf(")"))
                        .trim()
                        .split(","))
                .filter(n -> n.trim().split(" ").length == 2)
                .map(String::trim)
                .filter(n -> !startWithPrimate(n))
                .collect(Collectors.toMap(n -> n, "@RequestBody "::concat));

        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            str = str.replaceAll(stringStringEntry.getKey(), stringStringEntry.getValue());
        }

        return str;
    }


    static boolean startWithPrimate(String str) {
        return priType.stream().anyMatch(str::startsWith);
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
                .map(DubboToFeignUtil::facadeInterfaceAddFeign);
    }


    private static Optional<String> facadeImplAddRestAnnotation(String line) {
        return Optional.of(line)
                .filter(l -> l.contains("public class"))
                .map(l -> "@RestController".concat("\n ").concat(l));
    }


}
