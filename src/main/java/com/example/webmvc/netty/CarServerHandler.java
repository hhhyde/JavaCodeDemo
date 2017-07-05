package com.example.webmvc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
@ChannelHandler.Sharable
public class CarServerHandler extends SimpleChannelInboundHandler<String> {



    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        String client_IP = address.getAddress().getHostAddress();

        String body = msg + "";


        // 转发 推送?
//        NettyChannelMap.add(client_IP, (SocketChannel) ctx.channel()); // 保存当前的连接
//        NettyChannelMap.get(client_IP).writeAndFlush("push#"); // 推送


        // 响应
        String resultStr = body + "#"; // 返回消息以 # 结尾
        ByteBuf resp = Unpooled.copiedBuffer(resultStr.getBytes("UTF-8"));
        ctx.write(resp);

    }

    /*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     *
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " 连接上 !");

        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message !\n");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " 断开连接 !");
        // 长连接池中移除该连接
        NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

}
