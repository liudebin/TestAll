package qian.ling.yi.netty.api;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * TODO
 *
 * @date: 2018/12/25.
 * @author: guobin.liu@holaverse.com
 */

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    // 客户端连接服务器后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    // • 从服务器接收到数据后调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
            throws Exception {
        System.out.println("client 读取server数据..");
        // 服务端返回消息后
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为 :" + body);
    }

    // • 发生异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("client exceptionCaught..");
        // 释放资源
        ctx.close();
    }
}

