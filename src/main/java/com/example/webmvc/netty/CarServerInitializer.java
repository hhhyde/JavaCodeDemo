package com.example.webmvc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
public class CarServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private CarServerHandler carServerHandler;

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {

        ByteBuf delimiter = Unpooled.copiedBuffer("#".getBytes());// 以#结尾分割
        arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter)); // 长度不能超过1024

        arg0.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));   //编码成UTF-8
        arg0.pipeline().addLast(new ObjectEncoder());   //解析成对象
        arg0.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        arg0.pipeline().addLast(carServerHandler);
        // 超时监听
//        arg0.pipeline().addLast(new IdleStateHandler(4,5,7, TimeUnit.SECONDS));
//        arg0.pipeline().addLast(new HeartbeatServerHandler());
    }

}
