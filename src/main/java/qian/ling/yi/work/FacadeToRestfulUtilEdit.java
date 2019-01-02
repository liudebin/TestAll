package qian.ling.yi.work;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * FacadeToRestfulUtil
 *
 * @date: 2018/12/4.
 * @author: guobin.liu@holaverse.com
 */

public class FacadeToRestfulUtilEdit {


    /**
     * NIO读取百万级别文件
     *
     * @author Chillax
     */
    public static void main(String args[]) throws Exception {
//        String path = "D:/ideaProjects/ces";
//        String path = "D:/ideaProjects/rds/rds-api";
//        String path = "D:/ideaProjects/com/com-api";
//        String path = "D:/ideaProjects/mes/mes-api";
        String path = "D:/ideaProjects/ces/ces-api";
//
        getFileByType(path, "Facade.java")
                .forEach(file -> refactorLine(file, n -> FacadeToRestfulUtilEdit.editRestApi(file.getName().replace(".java", "") ,n))
                        .ifPresent(s -> writeLine(s, file)));
//        File file = new File("D:/ideaProjects/rds/rds-api/src/main/java/com/qihoo/finance/rds/modules/credible/facade/CredibleFacade.java");
//        refactorLine(file, n -> FacadeToRestfulUtilEdit.editRestApi(file.getName().replace(".java", "") ,n)).ifPresent(s->writeLine(s, file));

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

    private static void bufferWriteLine(BufferedWriter bufferedWriter, String str) {
        try {
            bufferedWriter.write(str + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String editRestApi(String base, String line) {
        return Optional.of(line).filter(l ->!(
                l.contains("RequestMapping")
//                        && l.substring(18,19).toUpperCase().equals(l.substring(18,19))
//                        && line.substring(18).startsWith(base))
                ))
                .map(l -> editPostMapping(base, l).orElse(l))
                .orElse(null);

    }




    public static List<File> getDir(String path) {
        return Arrays.stream(Objects.requireNonNull(new File(path).listFiles()))
                .filter(f ->f.isDirectory() && !f.getName().contains("."))
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
        return  Optional.of(dir)
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
        return getDir(path)
                .stream()
                .flatMap(n -> getFile(n, keyWord).stream())
                .collect(Collectors.toList());
    }


    static Optional<String> editPostMapping(String baseName, String line) {
        return Optional.of(line).filter(l -> l.contains("@PostMapping(\"/"))
                .map(str -> "\t@PostMapping(\"/" + baseName +"/"+ str.split("/")[1]);
    }

}
