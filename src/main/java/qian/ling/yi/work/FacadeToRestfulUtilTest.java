package qian.ling.yi.work;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * TODO
 *
 * @date: 2018/12/5.
 * @author: guobin.liu@holaverse.com
 */

public class FacadeToRestfulUtilTest {

    @Test
    public void getFacadeOrImplList() {
    }

    @Test
    public void addRequestAnnotation() {
        String  s = "Response<Map<Integer, List<Risk>>> queryRiskByDevice( String device,List<Integer> daysList);";
        Optional<String> s1 = FacadeToRestfulUtil.addRequestAnnotation(s);
        s1.ifPresent(System.out::print);
    }

    @Test
    public void addRequestAnnotationOld() {
        String  s = "Response<Map<Integer, List<Risk>>> queryRiskByDevice( String device,List<Integer> daysList);";
        Optional<String> s2 = FacadeToRestfulUtil.addRequestAnnotation(s);

    }

    @Test
    public void addRequestTypeStr() {
        assertThat(FacadeToRestfulUtil.addRequestTypeStr("(String bizTpye, String bizNo)"), is("(@RequestParam(\"bizTpye\")String bizTpye,@RequestParam(\"bizNo\") String bizNo)"));
        assertThat(FacadeToRestfulUtil.addRequestTypeStr("(String bizTpye, Bean bizNo)"), is("(@RequestParam(\"bizTpye\")String bizTpye,@RequestBody  Bean bizNo)"));

    }

    @Test
    public void addImplRequestType() {
        assertThat(FacadeToRestfulUtil.addImplRequestType("(String bizTpye, String bizNo)"), is("(String bizTpye, String bizNo)"));
        assertThat(FacadeToRestfulUtil.addImplRequestType("(String bizTpye, Bean bizNo)"), is("(String bizTpye, @RequestBody Bean bizNo)"));
    }

    @Test
    public void startWithPrimate() {
    }

    @Test
    public void addRestAnnotation() {
    }

    @Test
    public void interfaceAddFeignAndRequestMapping() {
    }

    @Test
    public void file2Rest() {
        FacadeToRestfulUtil.getFileByType("D:/ideaProjects/rds", "Facade")
                .forEach(System.out::println);
    }

    @Test
    public void fileImpl2Rest() {
        FacadeToRestfulUtil.getFileByType("D:/ideaProjects/ces", "FacadeImpl")
                .forEach(System.out::println);
    }

    @Test
    public void printHasServiceAnnotation() {
    }

    @Test
    public void main() {
    }

    @Test
    public void readWriteFileByLine() {
    }

    @Test
    public void writeFileByLine() {
    }

    @Test
    public void getDir(){
        FacadeToRestfulUtil.getDir("D:/ideaProjects/ces").forEach(System.out::println);
    }
}