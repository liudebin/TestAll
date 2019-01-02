package qian.ling.yi.netty.custom;

import com.alibaba.dubbo.common.Constants;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * TODO
 *
 * @date: 2018/12/21.
 * @author: guobin.liu@holaverse.com
 */

public class DubboNettyUse {
    private Bootstrap bootstrap;
    private static final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(Constants.DEFAULT_IO_THREADS, new DefaultThreadFactory("NettyClientWorker", true));
    private int timeout;

    public void doOpen(){
//        NettyHelper.setNettyLoggerFactory();
//        final NettyClientHandler nettyClientHandler = new NettyClientHandler(getUrl(), this);
        bootstrap = new Bootstrap();
        bootstrap.group(nioEventLoopGroup)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout())
                .channel(NioSocketChannel.class);

        if (timeout < 3000) {
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        } else {
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout);
        }

        bootstrap.handler(new ChannelInitializer() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
//                NettyCodecAdapter adapter = new NettyCodecAdapter(getCodec(), getUrl(), NettyClient.this);
                ch.pipeline()//.addLast("logging",new LoggingHandler(LogLevel.INFO))//for debug
//                        .addLast("decoder", adapter.getDecoder())
//                        .addLast("encoder", adapter.getEncoder())
//                        .addLast("handler", nettyClientHandler);
                ;
            }
        });
    }
}
