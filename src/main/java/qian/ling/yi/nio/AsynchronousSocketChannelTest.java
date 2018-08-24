package qian.ling.yi.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AsynchronousSocketChannelTest
 *
 * @author liuguobin
 * @date 2018/6/16
 */

public class AsynchronousSocketChannelTest {
    AsynchronousServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        int port = 8080;
        AsynchronousSocketChannelTest socketChannelTest = new AsynchronousSocketChannelTest(port);
    }

    AsynchronousSocketChannelTest(int port) {

        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void doAccept() {
        serverSocketChannel.accept(this, new AcceptCompletionHandler());
    }

    class AcceptCompletionHandler implements CompletionHandler {

        @Override
        public void completed(Object result, Object attachment) {

        }

        @Override
        public void failed(Throwable exc, Object attachment) {

        }
    }

}
