package qian.ling.yi.netty.officialInstance;

/**
 * Handles a server-side channel.
 *
 * @author liuguobin
 * @date 2017/8/3
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    /**
     * 静悄悄的消失
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//        // Discard the received data silently.
////        ((ByteBuf) msg).release(); // (3)
//
//    }


    /**
     * 只做打印
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf in = (ByteBuf) msg;
//        try {
//////            while (in.isReadable()) { // (1)
//////                System.out.print((char) in.readByte());
//////                System.out.flush(); //为什么用
//////                /**
//////                 * By default, System.out (a PrintStream) is line buffered, meaning that the output buffer is flushed when a newline character is encountered.
//////                 * This is important for interactivity,
//////                 * where you'd like to have an input prompt displayed before actually entering any input.
//////                 */
//////                /**
//////                 * PrintStream 被创建的时候，可以指定是否是autoFlush。如果不是，尽量flush下。如果是，则只要输出了一行，如printLn 或 输出 ‘\n’，都会自动flush
//////                 * 刷新下，是个比较好的习惯。
//////                 */
//////            }
//            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII)); // 等价于上边注释的
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }
//    }


    /**
     * 原样返回
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //A ChannelHandlerContext object provides various operations that enable you to trigger various I/O events and operations.
        ctx.write(msg);
        ctx.flush();
        //Did not release the received message . It is because Netty releases it for you when it is written out to the wire. - wire- 信息流
//        ctx.write(Object) does not make the message written out to the wire. It is buffered internally, and then flushed out to the wire by ctx.flush(). Alternatively, you could call ctx.writeAndFlush(msg) for brevity.
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}