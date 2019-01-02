package qian.ling.yi.work;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @date: 2018/12/29.
 * @author: guobin.liu@holaverse.com
 */

public class FacadeMockMethodUtil {
//        static String testPath = "D:\\ideaProjects\\rds\\rds-app\\src\\test\\java\\feignTest\\";
    public static void main(String[] args) {
//        String path = "D:/ideaProjects/rds";

//        setFeignClient("com-app", "com");
        String path = "D:\\ideaProjects\\rds\\rds-api\\src\\main\\java\\com\\qihoo\\finance\\rds\\modules\\detect\\facade\\DeviceBlackListFacade.java";
        File temp = new File(path);
        Optional<List<String>> strings = FileUtil.refactorLine(temp, FacadeMockMethodUtil::doNothing)
                .map(Collection::stream)
                .map(lineStream -> lineStream.map(FacadeMockMethodUtil::methodClone).filter(Optional::isPresent).map(Optional::get))
                .map(n -> n.collect(Collectors.toList()));

        strings.map(n ->FileUtil.refactorLine(temp, FacadeMockMethodUtil::removeEnd)
                                .map(m -> {
                                    m.addAll(n);
                                    m.add("\n}");
                                    return m;
                                }).orElse(new ArrayList<>()))
                .ifPresent(h -> FileUtil.writeLine(h,temp ));

//        FileUtil.getFileByType(path, "Facade.java")
//                .forEach(file -> FileUtil.refactorLine(file, FileUtil::response)
//                        .ifPresent(n->n.stream().filter(Objects::nonNull).forEach(System.out::println)));
//

    }

    public static void mockMethod(String projectPath, String testPath) {
        FileUtil.getFileByTypeNew(projectPath, f-> f.getName().endsWith("Facade.java") && !f.getName().contains("Ext"))
                .forEach(file -> consumeFile(file, projectPath, testPath));

    }


    public static void refactorInterface(File temp, List<String> lines) {
        FileUtil.refactorLine(temp, FacadeMockMethodUtil::removeEnd)
                .map(m -> {
                    m.addAll(lines);
                    m.add("\n}");
                    return m;
                })
                .ifPresent(h -> FileUtil.writeLine(h,temp ));
    }

    public static void consumeFile(File file, String path, String testPath) {
        List<String> strings = interfaceMethodClone(file).get();
        refactorInterface(file, strings);
        refactorImpl(path, file, strings);
        createTestFile(file, strings, testPath);

    }
    public static void refactorImpl(String path, File file, List<String> lines) {
        FileUtil.getFileByTypeNew(path, f-> f.getName().endsWith("FacadeImpl.java") && !f.getName().contains("Ext"))
                .stream()
                .filter(file1 -> file1.getName().contains(getFileName(file.getName(), 0, ".")))
                .forEach(file2 -> writeImplFile(file2, lines));
    }

    public static void writeImplFile(File file, List<String> lines) {

        FileUtil.refactorLine(file, FacadeMockMethodUtil::removeEnd)
                .map(m -> {
                    m.addAll(getImplImpl(lines));
                    m.add("\n}");
                    return m;
                })
                .ifPresent(h -> FileUtil.writeLine(h,file ));
    }

    public static Optional<List<String>> interfaceMethodClone(File file){
        return  FileUtil.refactorLine(file, FacadeMockMethodUtil::doNothing)
                .map(Collection::stream)
                .map(lineStream -> lineStream.map(FacadeMockMethodUtil::methodClone).filter(Optional::isPresent).map(Optional::get))
                .map(n -> n.collect(Collectors.toList()));
    }

    public static List<String> getImplImpl(List<String> methodClones ){
        return methodClones.stream()
                .filter(n ->!n.contains("@PostMapping"))
                .map(n-> "\t@Override\n"+ n.replaceAll("@RequestParam\\(\"\\w*\"\\)", "") + "{\n"
                        + "\t\t" + "Map<String, Object> map = new HashMap<>();\n" +
                        "    \tmap.put(\"\", );\n" +
                        "\t\treturn new Response<>().success(map);\n" + "\t}\n"+"\n"
                ).collect(Collectors.toList());

    }

    public static void createTestFile(File file, List<String> methodClones, String testPath){
        String fileName = getFileName(file.getName(), 0, ".");
        String var = fileName.substring(0, 1).toLowerCase() + fileName.substring(1);
        String  s = "package com.example.demo;\n" +
                "\n" +
                "import com.qihoo.finance.msf.api.domain.Response;\n" +
                "import org.hamcrest.CoreMatchers;\n" +
                "import org.junit.Assert;\n" +
                "import org.junit.Test;\n" +
                "import org.junit.runner.RunWith;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.boot.test.context.SpringBootTest;\n" +
                "import org.springframework.test.context.junit4.SpringRunner;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "@RunWith(SpringRunner.class)\n" +
                "@SpringBootTest\n" +
                "public class " +
                getTestFileName(file.getName()) +
                " {\n" +
                "\n" +
                "    @Autowired\n" +

                "\t" + fileName+ " " + var +";\n" ;

        String collect = methodClones.stream()
                .filter(n ->!n.contains("@PostMapping"))
                .map(n -> buildTestMethod(file, n)).collect(Collectors.joining());
        s = s + collect + "\n}";
        System.out.println(s);
        File fOut = new File(testPath+ getTestFileName(file.getName())+ ".java");
        System.out.println(fOut.getPath());
        FileUtil.write(s, fOut);
    }

    private static String buildTestMethod(File file, String n) {
        String fileName = getFileName(file.getName(), 0,".");
        return "\n\n\t@Test\n" +
                "    public void test" + getMethodName(n).get() + "(){\n\t\t"
                + getParam(n)
                + " \n\t\t\t= FeignTestUtil.buildBean(.class);\n"
                + "\t\tResponse<Map> mapResponse = " + fileName.substring(0, 1).toLowerCase() + fileName.substring(1)
                +"."+ getMethodName(n).get() + "("+getParam(n)+ ");\n"
                + "        Assert.assertEquals(FeignTestUtil.propertiesNotEqual(, mapResponse.getData().get(\"\")), CoreMatchers.is(false));\n" +
                "\t}";
    }

    private static String getParam(String n) {
        return n.substring(n.indexOf("(") + 1, n.lastIndexOf(")"))
        .replaceAll("@RequestParam\\(\"\\w*\"\\)", "")
        .replaceAll("@RequestBody ", "").trim();
    }

    public static  String getTestFileName(String fileName) {
        return getFileName(fileName, 0, ".") + "FeignTestCase";
    }

    private static String getFileName(String fileName, int i, String s) {
        return fileName.substring(i, fileName.lastIndexOf(s));
    }


    private static String removeEnd(String line) {
        return line.startsWith("}") ? null : line;
    }
    static String doNothing(String line) {
       return line;
    }

    static Optional<String> methodClone(String line) {
        return Optional.ofNullable(methodAnnotationClone(line).orElse(methodDeclareClone(line).orElse(null)));
    }

    static Optional<String> methodAnnotationClone(String line) {
        return Optional.of(line).filter(n -> n.contains("@PostMapping")).map(str -> "\n\t" + str.substring(0, str.lastIndexOf("\")")).trim().concat("Clone\")"));
    }

    static Optional<String> methodDeclareClone(String line) {
        return getMethodName(line).map(n -> refactorMethod(n, line));
    }

    public static String refactorMethod(String methodName, String line) {
        String newLine = "\t";
        if (line.contains("public")){
            newLine += "public";
        }
        return newLine + " Response<Map> " + methodName + "Clone" + line.substring(line.indexOf(methodName) + methodName.length(), line.length());

    }

    private static Optional<String> getMethodName(String line) {
        return Optional.of(line)
                .filter(str -> str.contains("("))
                .filter(l -> l.contains("Response") || l.contains("@RequestBody") || l.contains("@RequestParam"))
                .map(str -> str.substring(0, str.indexOf("("))
                .trim()
                .split(" "))
                .filter(n ->n.length>1)
                .map(n -> n[n.length-1]);
    }




}
