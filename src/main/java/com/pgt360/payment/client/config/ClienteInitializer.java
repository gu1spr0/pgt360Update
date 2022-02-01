package com.pgt360.payment.client.config;

import com.pgt360.payment.client.handler.ClienteHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteInitializer extends ChannelInitializer<SocketChannel> {
    private final ClienteHandler clienteHandler = new ClienteHandler();
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //pipeline.addLast("decoder", new NettyDecode());
        //pipeline.addLast("encoder", new NettyEncode());
        pipeline.addLast(clienteHandler);
    }
}
