package qian.ling.yi.netty.SourceTest.byteBuf;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * ByteBufferTest
 *
 * @author liuguobin
 * @date 2018/6/16
 */

public class ByteBufferTest {
    @Test
    public void testBase() {
        ByteBuffer buffer = ByteBuffer.allocate(88);
        String v = "netty 权威指南";
        buffer.put(v.getBytes());
//        buffer.flip();
        byte[] vArray = new byte[buffer.remaining()];
        buffer.get(vArray);
        String decodeValue = new String(vArray);
        System.out.println(decodeValue);
    }
}
