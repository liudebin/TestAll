package qian.ling.yi.work;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @date: 2018/12/29.
 * @author: guobin.liu@holaverse.com
 */

public class FacadeMockMethodUtilTest {

    @Test
    public void doNothing() {
    }

    @Test
    public void methodClone() {
//        String s = "    @PostMapping(\"/DeviceBlackListFacade/saveClone\")";
//        String s = "    Response<Map> save(@RequestBody DeviceBlackList deviceBlackList);";
        String s = "    Response<Map> dafadfadf;";
        FacadeMockMethodUtil.methodClone(s).ifPresent(System.out::println);
    }

    @Test
    public void methodAnnotationClone() {
        String s = "    @PostMapping(\"/DeviceBlackListFacade/save\")";
        FacadeMockMethodUtil.methodAnnotationClone(s).ifPresent(System.out::println);
    }

    @Test
    public void methodDeclareClone() {
    }


    @Test
    public void interfaceMethodClone() {
        String path = "D:\\ideaProjects\\rds\\rds-api\\src\\main\\java\\com\\qihoo\\finance\\rds\\modules\\detect\\facade\\DeviceBlackListFacade.java";
        File temp = new File(path);
        Optional<List<String>> strings = FacadeMockMethodUtil.interfaceMethodClone(temp);
        strings.ifPresent(n -> n.forEach(System.out::println));

    }

    @Test
    public void getImplImpl() {
        String path = "D:\\ideaProjects\\rds\\rds-api\\src\\main\\java\\com\\qihoo\\finance\\rds\\modules\\detect\\facade\\DeviceBlackListFacade.java";
        File temp = new File(path);
        Optional<List<String>> strings = FacadeMockMethodUtil.interfaceMethodClone(temp);
        strings.map(FacadeMockMethodUtil::getImplImpl).ifPresent(n -> n.forEach(System.out::println));

    }

    @Test
    public void buildTestCase() {
        String path = "D:\\ideaProjects\\rds\\rds-api\\src\\main\\java\\com\\qihoo\\finance\\rds\\modules\\detect\\facade\\DeviceBlackListFacade.java";
        File temp = new File(path);
        Optional<List<String>> strings = FacadeMockMethodUtil.interfaceMethodClone(temp);
        strings.ifPresent(n -> FacadeMockMethodUtil.createTestFile(temp, n, ""));

    }

    @Test
    public void testRds() {
        String testPath = "D:\\ideaProjects\\demo\\src\\test\\java\\com\\example\\demo\\";
        String path = "D:/ideaProjects/rds";
        FacadeMockMethodUtil.mockMethod(path, testPath);
    }


}