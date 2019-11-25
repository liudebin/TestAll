package qian.ling.yi.file;

import org.junit.Test;
import qian.ling.yi.AbstractTest;
import qian.ling.yi.util.StringUtil;
import sun.security.provider.ConfigFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liuguobin on 2016/12/23.
 */
public class FileUtil extends AbstractTest{

    /**
     * 并没有像其他人说的只能写两层
     */
    @Test
    public void testMkdir() {
        File file = new File("/JAVA_Files/ftp/localFile/a/b/c/d");
        if (!file.exists()) {
            logger.info("{}", file.mkdirs());
        }
    }

    /**
     * 根据文件路径，获取文件名称
     * @param path
     * @return
     */
    public static String getFileNameByPath(String path) {
        if (StringUtil.isEmpty(path)) {
            return path;
        }
        String[] fileNames = path.split(File.separator);
        return fileNames[fileNames.length -1];
    }


    public List<String> readFileByLine(File file) {
//        File file = new File("/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test");
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        BufferedReader bufReader = null;
        try {
            // FileReader:用来读取字符文件的便捷类。
            bufReader = new BufferedReader(new FileReader(file));
            return bufReader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
        return Collections.emptyList();

    }


      static List<File> listFileFromDir(String path, String keyWord) {
        List<File> targetList = new ArrayList<>();

        File file = new File(path);
        File[] files = file.listFiles();
        if (file.exists() && null != files ) {
            Arrays.stream(files)
                    .filter(File::isDirectory)
                    .forEach(file2 -> {
                                collectFiles(file2, keyWord).ifPresent(targetList::addAll);
                            }
                    );
        }
        return targetList;
    }

    public static Optional<List<File>> collectFiles(File file, String key) {
        if (file.exists()
                && file.isDirectory()) {

            File[] files = file.listFiles();

            if (null != files) {
                List<File> facades = Arrays.stream(files)
                        .filter(f -> f.getName().endsWith(key+".java"))
                        .collect(Collectors.toList());
                return Optional.of(facades);
            }
        }
        return Optional.empty();
    }



    String fetchParent(List<String> lines) {
        return lines.stream().filter(line -> line.contains(" implements "))
                .map(line -> line.substring(line.indexOf("implements ")+11, line.lastIndexOf("{")).trim())
                .map(line -> {
                    if (line.contains(".")) {
                        String[] split = line.split("\\.");
                        return split[split.length - 1];
                    } else {
                        return line;
                    }
                })
                .findFirst().orElse(null);
    }

    Stream<String> fetchAttrs(List<String> lines) {
        return lines.stream().filter(line -> line.contains(" get"))
                .map(line -> line.substring(line.indexOf(" get") + 4, line.lastIndexOf("(")).trim());
    }


    @Test
    public void test(){
        File file = new File("D:\\ideaProjects\\dcs\\dcs-model-risk\\src\\main\\java\\com\\qihoo\\finance\\dcs\\model\\apv");
        Optional<List<File>> files = collectFiles(file, "");

        Map<String, List<String>> fileListMap = files.map(li -> li.stream().collect(Collectors.toMap(File::getName,
                this::readFileByLine))).orElse(null);

        File cnFileInterface = new File("D:\\ideaProjects\\dcs\\dcs-model-risk\\src\\main\\java\\奇步天下\\风控数据模型");
        List<File> cnFiles = collectFiles(cnFileInterface, "").orElse(Collections.emptyList());


        fileListMap.entrySet().forEach(fileLine -> {
//                    System.out.println(fileLine.getKey());
                    String fileName = fileLine.getKey().split(".java")[0];
                    List<String> lines = fileLine.getValue();
                    String parentName = fetchParent(lines);

                    cnFiles.stream().filter(cnFile -> cnFile.getName().equals(parentName + ".java"))
                            .map(this::readFileByLine)
                            .flatMap(this::fetchAttrs)
                            .map(attr -> {
                                for (int i = 0; i < lines.size(); i++) {
                                    String line = lines.get(i);
                                    if (line.contains("get") && line.contains(attr)) {
                                        String nextLine = lines.get(i + 1);
                                        String enAttr = "";
                                        String type = line.substring(line.indexOf("public ") + 7, line.indexOf(" get"));
                                        if (nextLine.contains("(")) {
                                            enAttr = nextLine.substring(nextLine.indexOf(" get") + 4,
                                                    nextLine.lastIndexOf("(")).trim();
                                        }
                                        return fileName + " " +parentName + " " +  enAttr + " " + attr +" " +type+" " +type;
                                    }
                                }
                                return "";
                            }).forEach(System.out::println);
                }
            );



    }


    @Test
    public void testPhl(){
        File file = new File("D:\\ideaProjects\\dcs\\dcs-model-risk\\src\\main\\java\\com\\qihoo\\finance\\dcs\\model" +
                "\\apv\\phl");
        Optional<List<File>> files = collectFiles(file, "");

        Map<String, List<String>> fileListMap = files.map(li -> li.stream().collect(Collectors.toMap(File::getName,
                this::readFileByLine))).orElse(null);

        File cnFileInterface = new File("D:\\ideaProjects\\dcs\\dcs-model-risk\\src\\main\\java\\奇步天下\\风控数据模型\\菲律宾");
        List<File> cnFiles = collectFiles(cnFileInterface, "").orElse(Collections.emptyList());


        fileListMap.entrySet().forEach(fileLine -> {
//                    System.out.println(fileLine.getKey());
                    String fileName = fileLine.getKey().split(".java")[0];
                    List<String> lines = fileLine.getValue();
                    String parentName = fetchParent(lines);

                    cnFiles.stream().filter(cnFile -> cnFile.getName().equals(parentName + ".java"))
                            .map(this::readFileByLine)
                            .flatMap(this::fetchAttrs)
                            .map(attr -> {
                                for (int i = 0; i < lines.size(); i++) {
                                    String line = lines.get(i);
                                    if (line.contains("get") && line.contains(attr)) {
                                        String nextLine = lines.get(i + 1);
                                        String enAttr = "";
                                        String type = line.substring(line.indexOf("public ") + 7, line.indexOf(" get"));
                                        if (nextLine.contains("(")) {
                                            enAttr = nextLine.substring(nextLine.indexOf(" get") + 4,
                                                    nextLine.lastIndexOf("(")).trim();
                                        }
                                        return fileName + " " +parentName + " " +  enAttr + " " + attr +" " +type+" " +type;
                                    }
                                }
                                return "";
                            }).forEach(System.out::println);
                }
        );



    }

//    public String fetchLineContainKey() {
//
//
//    }




}
