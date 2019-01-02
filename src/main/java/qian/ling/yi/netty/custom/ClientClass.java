package qian.ling.yi.netty.custom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import qian.ling.yi.netty.officialInstance.TimeClientHandler;

/**
 * TimeClient
 *
 * @author liuguobin
 * @date 2018/12/25
 */

public class ClientClass {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8989;
        //1、 初始化用于连接及I/O工作的”线程池”；
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //初始化Bootstrap实例， 此实例是netty客户端应用开发的入口
            Bootstrap client = new Bootstrap();
            // 通过Bootstrap的group方法，设置初始化的”线程池”；
            client.group(workerGroup);
            // 4、 指定通道channel的类型，由于是客户端，故而是NioSocketChannel；
            client.channel(NioSocketChannel.class);
            // 5、 设置SocketChannel的选项
            client.option(ChannelOption.SO_KEEPALIVE, true);
            //6、 设置SocketChannel的处理器， 其内部是实际业务开发的”主战场”
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // 7、 连接指定的服务地址；
            ChannelFuture f = client.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
