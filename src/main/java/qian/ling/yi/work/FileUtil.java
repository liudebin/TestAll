package qian.ling.yi.work;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @date: 2018/12/20.
 * @author: guobin.liu@holaverse.com
 */

public class FileUtil {


    public static void main(String args[]) throws Exception {
//        String path = "D:/ideaProjects/ces";
//        String path = "D:/ideaProjects/com";
//        String path = "D:/ideaProjects/rds";
        String path = "D:/ideaProjects/mes";

//        setFeignClient("com-app", "com");
//        getFileByType(path, "Facade.java")
//                .forEach(file -> readLine(file, DubboToFeignUtil::refactorFacadeLine)
//                        .ifPresent(s -> writeLine(s, file)));
//
//        getFileByType(path, "FacadeImpl.java")
//                .forEach(file -> readLine(file, DubboToFeignUtil::refactorFacadeImplLine)
//                        .ifPresent(s -> writeLine(s, file)));
        getFileByType(path, "Facade.java")
                .forEach(file -> refactorLine(file, FileUtil::response)
                        .ifPresent(n->n.stream().filter(Objects::nonNull).forEach(System.out::println)));


    }



    public static Optional<List<String>> refactorLine(File file, Function<String, String> function) {
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

    public static void write(String content, File fOut) {
        try (FileWriter fileWriter = new FileWriter(fOut);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
             bufferWriteLine(bufferedWriter, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendLine(List<String> lines, File file) {
//        File fOut = new File(file.getAbsolutePath() + ".tmp");
        try (FileWriter fileWriter = new FileWriter(file,true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            lines.forEach(
                    str -> Optional.ofNullable(str)
                            .ifPresent(s -> bufferWriteLine(bufferedWriter, str))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
//        file.delete();
//        fOut.renameTo(file);

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

    public static List<File> getFileNew(File dir, Predicate<File> predicate) {
        return Optional.of(dir)
                .map(File::listFiles)
                .map(Arrays::stream)
                .map(stream -> stream
                        .filter(predicate)
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

    public static List<File> getFileByTypeNew(String path, Predicate<File> predicate) {
        return Stream.of(getDir(path), Collections.singletonList(new File(path)))
                .flatMap(Collection::stream)
                .flatMap(n -> getFileNew(n, predicate).stream())
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


    static Optional<String> implAddPostMapping(String line) {
        return Optional.of(line)
                .filter(l -> l.contains("(") && l.contains("public"))
                .map(DubboToFeignUtil::addImplRequestType);
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
