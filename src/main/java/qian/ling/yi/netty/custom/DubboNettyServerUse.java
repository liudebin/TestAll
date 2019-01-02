package qian.ling.yi.netty.custom;

import com.alibaba.dubbo.common.Constants;
//import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.transport.netty.NettyServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.function.Function;

/**
 * TODO
 *
 * @date: 2018/12/21.
 * @author: guobin.liu@holaverse.com
 */

public class DubboNettyServerUse {
    private ServerBootstrap bootstrap;
    private static final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(Constants.DEFAULT_IO_THREADS, new DefaultThreadFactory("NettyClientWorker", true));
    private int timeout;
//    private Map<String, Channel> channels;

    private io.netty.channel.Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;



    private volatile URL url;
    private InetSocketAddress bindAddress;




    protected void doOpen() throws Throwable {
//        NettyHelper.setNettyLoggerFactory();

        bootstrap = new ServerBootstrap();

        bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
        workerGroup = new NioEventLoopGroup(getUrl().getPositiveParameter(Constants.IO_THREADS_KEY, Constants.DEFAULT_IO_THREADS),
                new DefaultThreadFactory("NettyServerWorker", true));

//        final NettyServerHandler nettyServerHandler = new NettyServerHandler(getUrl(), this);

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        NettyCodecAdapter adapter = new NettyCodecAdapter(getCodec(), getUrl(), NettyServer.this);
                        ch.pipeline()//.addLast("logging",new LoggingHandler(LogLevel.INFO))//for debug
//                                .addLast("decoder", adapter.getDecoder())
//                                .addLast("encoder", adapter.getEncoder())
//                                .addLast("handler", nettyServerHandler);
                        ;
                    }
                });
        // bind
        ChannelFuture channelFuture = bootstrap.bind(getBindAddress());
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();

    }


//    @Override
    public URL getUrl() {
        return url;
    }

    public InetSocketAddress getBindAddress() {
        return bindAddress;
    }
}
