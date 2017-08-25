package qian.ling.yi.file;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.io.File;
import java.net.URL;

/**
 * 路径加载
 *
 * @author liuguobin
 * @date 2017/8/25
 */

public class FilePathTest extends AbstractTest {
    @Test
    public void testPath()throws Exception {
//        第一种：
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);
//        结果:
//        /Users/liuguobin/IdeaProjects/TestAll/target/classes
//        获取当前工程 class绝对路径;

//        如果不加“/”
        File f1 = new File(this.getClass().getResource("").getPath());
        System.out.println(f1);

//        结果：
//        /Users/liuguobin/IdeaProjects/TestAll/target/classes/qian/ling/yi/file
//        获取当前class的包的绝对路径；
//
//        第二种：
        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath() ;
        System.out.println(courseFile);
//        结果：
//        /Users/liuguobin/IdeaProjects/TestAll
//                获取当前类的所在工程的绝对路径;
//
//        第三种：
        URL xmlpath = this.getClass().getClassLoader().getResource("logback.xml");
        System.out.println(xmlpath);
//        结果：
//        file:/Users/liuguobin/IdeaProjects/TestAll/target/classes/logback.xml
//        获取当前工程class目录下logback.xml文件的路径
//
//        第四种：
        System.out.println(System.getProperty("user.dir"));
//        结果：
//        /Users/liuguobin/IdeaProjects/TestAll
//                获取当前工程的绝对路径
//
//        第五种：
        System.out.println( System.getProperty("java.class.path"));
//        结果：
//        /Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar:/Applications/IntelliJ IDEA.app/Contents/plugins/junit/lib/junit-rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/lib/tools.jar:/Users/liuguobin/IdeaProjects/TestAll/target/classes:/Users/liuguobin/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar:/Users/liuguobin/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/Users/liuguobin/.m2/repository/commons-net/commons-net/3.5/commons-net-3.5.jar:/Users/liuguobin/.m2/repository/com/jcraft/jsch/0.1.54/jsch-0.1.54.jar:/Users/liuguobin/.m2/repository/org/apache/commons/commons-pool2/2.3/commons-pool2-2.3.jar:/Users/liuguobin/.m2/repository/org/apache/httpcomponents/httpclient/4.5.2/httpclient-4.5.2.jar:/Users/liuguobin/.m2/repository/commons-codec/commons-codec/1.9/commons-codec-1.9.jar:/Users/liuguobin/.m2/repository/org/apache/httpcomponents/httpcore/4.4.4/httpcore-4.4.4.jar:/Users/liuguobin/.m2/repository/org/apache/zookeeper/zookeeper/3.4.6/zookeeper-3.4.6.jar:/Users/liuguobin/.m2/repository/log4j/log4j/1.2.16/log4j-1.2.16.jar:/Users/liuguobin/.m2/repository/jline/jline/0.9.94/jline-0.9.94.jar:/Users/liuguobin/.m2/repository/io/netty/netty/3.7.0.Final/netty-3.7.0.Final.jar:/Users/liuguobin/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar:/Users/liuguobin/.m2/repository/ch/qos/logback/logback-core/1.1.7/logback-core-1.1.7.jar:/Users/liuguobin/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar:/Users/liuguobin/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/Users/liuguobin/.m2/repository/xml-apis/xml-apis/1.0.b2/xml-apis-1.0.b2.jar:/Users/liuguobin/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/liuguobin/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/liuguobin/.m2/repository/org/mockito/mockito-core/2.8.9/mockito-core-2.8.9.jar:/Users/liuguobin/.m2/repository/net/bytebuddy/byte-buddy/1.6.14/byte-buddy-1.6.14.jar:/Users/liuguobin/.m2/repository/net/bytebuddy/byte-buddy-agent/1.6.14/byte-buddy-agent-1.6.14.jar:/Users/liuguobin/.m2/repository/org/objenesis/objenesis/2.5/objenesis-2.5.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-context/4.3.3.RELEASE/spring-context-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-aop/4.3.3.RELEASE/spring-aop-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-beans/4.3.3.RELEASE/spring-beans-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-core/4.3.3.RELEASE/spring-core-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-expression/4.3.3.RELEASE/spring-expression-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-web/4.3.3.RELEASE/spring-web-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/com/taobao/pamirs/schedule/tbschedule/3.3.3.2/tbschedule-3.3.3.2.jar:/Users/liuguobin/.m2/repository/commons-lang/commons-lang/2.4/commons-lang-2.4.jar:/Users/liuguobin/.m2/repository/commons-beanutils/commons-beanutils-core/1.7.0/commons-beanutils-core-1.7.0.jar:/Users/liuguobin/.m2/repository/commons-collections/commons-collections/2.0/commons-collections-2.0.jar:/Users/liuguobin/.m2/repository/com/google/code/gson/gson/2.1/gson-2.1.jar:/Users/liuguobin/.m2/repository/com/alibaba/fastjson/1.2.14/fastjson-1.2.14.jar:/Users/liuguobin/.m2/repository/com/alibaba/rocketmq/rocketmq-client/3.6.2.Final/rocketmq-client-3.6.2.Final.jar:/Users/liuguobin/.m2/repository/org/springframework/spring-test/4.3.3.RELEASE/spring-test-4.3.3.RELEASE.jar:/Users/liuguobin/.m2/repository/io/netty/netty-all/4.1.13.Final/netty-all-4.1.13.Final.jar:/Users/liuguobin/.m2/repository/com/google/guava/guava/23.0/guava-23.0.jar:/Users/liuguobin/.m2/repository/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar:/Users/liuguobin/.m2/repository/com/google/errorprone/error_prone_annotations/2.0.18/error_prone_annotations-2.0.18.jar:/Users/liuguobin/.m2/repository/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar:/Users/liuguobin/.m2/repository/org/codehaus/mojo/animal-sniffer-annotations/1.14/animal-sniffer-annotations-1.14.jar:/Users/liuguobin/.m2/repository/com/netflix/hystrix/hystrix-core/1.5.12/hystrix-core-1.5.12.jar:/Users/liuguobin/.m2/repository/com/netflix/archaius/archaius-core/0.4.1/archaius-core-0.4.1.jar:/Users/liuguobin/.m2/repository/commons-configuration/commons-configuration/1.8/commons-configuration-1.8.jar:/Users/liuguobin/.m2/repository/io/reactivex/rxjava/1.2.0/rxjava-1.2.0.jar:/Users/liuguobin/.m2/repository/org/hdrhistogram/HdrHistogram/2.1.9/HdrHistogram-2.1.9.jar:/Users/liuguobin/.m2/repository/com/lmax/disruptor/3.3.6/disruptor-3.3.6.jar:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar
//                获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperties());

        System.out.println(this.getClass().getPackage());
    }
}
