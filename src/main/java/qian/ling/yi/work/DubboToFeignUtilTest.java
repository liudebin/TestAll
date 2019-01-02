package qian.ling.yi.work;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @date: 2018/12/13.
 * @author: guobin.liu@holaverse.com
 */

public class DubboToFeignUtilTest {

    @Test
    public void RdsPom() {
        DubboToFeignUtil.getFileByType("D:/ideaProjects/rds", "pom.xml")
                .forEach(file -> DubboToFeignUtil.readLine(file, DubboToFeignUtil::filterApi));
    }
    @Test
    public void MesPom() {
        DubboToFeignUtil.getFileByType("D:/ideaProjects/mes", "pom.xml")
                .forEach(file -> DubboToFeignUtil.readLine(file, DubboToFeignUtil::filterApi));
    }

    @Test
    public void igsPom() {
        DubboToFeignUtil.getFileByType("D:/ideaProjects/igs", "pom.xml")
                .forEach(file -> DubboToFeignUtil.readLine(file, DubboToFeignUtil::filterApi));
    }

    @Test
    public void writeLine() {
    }

    @Test
    public void getImport() {
    }

    @Test
    public void getImplImport() {
    }

    @Test
    public void getFacadeOrImplList() {
    }

    @Test
    public void getDir() {
    }

    @Test
    public void getFile() {
    }

    @Test
    public void getFileByType() {
        String l = "@RequestMapping(\"/CredibleFacade\")";
        System.out.println(l.startsWith("@RequestMapping") );
        String s = l.substring(18,19);
        System.out.println(l.substring(18));
        System.out.println((s).toUpperCase().equals(s));
        System.out.println(l.startsWith("@RequestMapping") && l.substring(17,18).toUpperCase().equals(l.substring(17,18)));


        System.out.println(JSON.toJSONString( "@PostMapping(\"/getUserNoListByFingerPrint\")".split("/")[1]));
    }
}