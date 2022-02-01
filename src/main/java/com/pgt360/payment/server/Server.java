package com.pgt360.payment.server;

import com.pgt360.payment.client.Cliente;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Slf4j
@RequiredArgsConstructor
@Component
public class Server {
    private final ServerBootstrap serverBootstrap;
    private final InetSocketAddress tcpSocketAddress;
    Channel serverChannel;
    private ChannelFuture channelFuture;
    public ChannelFuture start(){
        try{
            channelFuture = serverBootstrap.bind(tcpSocketAddress).sync();
            log.info("*************************************************");
            log.info("**********Server iniciado : port {}**********", tcpSocketAddress.getPort());
            log.info("*************************************************");
            //channel = (Channel)channelFuture.channel().closeFuture().sync();
            //channelFuture.channel().closeFuture().sync();
            serverChannel = (Channel) channelFuture.channel().closeFuture().sync().channel();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return channelFuture;
    }

    @PreDestroy
    public void stop(){
        if(serverChannel != null){
            serverChannel.close();
            serverChannel.parent().close();
        }
    }
}
