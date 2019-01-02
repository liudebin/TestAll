package qian.ling.yi.netty.custom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import qian.ling.yi.netty.officialInstance.DiscardServerHandler;

/**
 * TODO
 *
 * @date: 2018/12/25.
 * @author: guobin.liu@holaverse.com
 */

public class ServerClass {
    private final int port;

    public ServerClass(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        //1、 初始化用于Acceptor的主”线程池”；
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        //2. 初始化 用于Channel的I/O工作的从”线程池”
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            //3. 初始化ServerBootstrap实例，引导、绑定和启动服务器。是netty服务端应用开发的入口。-- >
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //4. 将NioEventLoopGroup，设定为serverBootstrap的acceptor 和 client
            // 处理channel的所有事件和IO
            // 设置InetSocketAddress让服务器监听某个端口已等待客户端连接。
            serverBootstrap.group(parentGroup, childGroup)
                    //5. 指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel；
                    .channel(NioServerSocketChannel.class)
                    // 6. 设置ServerSocketChannel的处理器
                    .handler(new LoggingHandler())
//                    .localAddress("localhost", port)
                    //6、设置子通道也就是SocketChannel的处理器， 其内部是实际业务开发的”主战场”
                    .childHandler(new ChannelInitializer<Channel>() {
                        //设置childHandler执行所有的连接请求
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // 配置ServerSocketChannel的选项
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 配置子通道也就是SocketChannel的选项
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定服务器，
            // 调用sync()方法会阻塞直到服务器完成绑定。
            //9、 绑定并侦听某个端口
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully().sync();
            childGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new ServerClass(20000).start();
    }
}


