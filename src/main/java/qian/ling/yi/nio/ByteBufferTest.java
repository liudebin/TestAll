package qian.ling.yi.nio;

import org.junit.Test;
import qian.ling.yi.AbstractTest;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuguobin on 2016/12/27.
 */
public class ByteBufferTest extends AbstractTest {

    @Test
    public void testPut() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String s = "刘国彬iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiA";
        byteBuffer.put(s.getBytes("gbk"));
        logger.info("{}", byteBuffer.position());
        byte[] bytes = byteBuffer.array();
        String result = new String(bytes, "gbk");
        logger.info(result);
        String s1 = "刘刘国彬iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiA";
        byteBuffer.flip();
        byteBuffer.put(s1.getBytes("gbk"));
        bytes = byteBuffer.array();
        result = new String(bytes, "gbk");
        logger.info(result);
//        logger.info(result.get);

    }

    @Test
    public void testWrap() throws Exception {
        String s = "刘刘国彬iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiA";
        String s1 = "刘国彬iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiA";
        ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes("gbk"));
        logger.info("{}", byteBuffer.position());
        byte[] bytes = byteBuffer.array();
        String result = new String(bytes, "gbk");
//        logger.info(new String(byteBuffer.asReadOnlyBuffer().array(), "gbk"));
        logger.info(result);
        byteBuffer.flip();
        FileOutputStream out = new FileOutputStream(new File("/Users/liuguobin/IdeaProjects/pay/src/main/resources/interface/kq/file/test"));
        FileChannel outCh = out.getChannel();
        outCh.write(byteBuffer);
//        byteBuffer.flip();
        byteBuffer.put(s1.getBytes("gbk"));
        bytes = byteBuffer.array();
        result = new String(bytes, "gbk");
        logger.info(result);

    }

    @Test
    public void test() {
        StringBuilder sb = new StringBuilder(450);
        for (int i = 0 ; i < 450;i ++) {
            sb.append("i");
        }
        logger.info(sb.toString());
    }
}
