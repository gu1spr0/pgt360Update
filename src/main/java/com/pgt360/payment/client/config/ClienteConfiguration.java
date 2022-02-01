package com.pgt360.payment.client.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ClienteProperties.class)
public class ClienteConfiguration {
    private final ClienteProperties clienteProperties;
    @Bean(name = "bootstrap")
    public Bootstrap bootstrap(ClienteInitializer clienteInitializer) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group());
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(clienteInitializer);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, clienteProperties.isKeepAlive());
        /*ChannelFuture f = bootstrap.connect(nettyProperties.getHost(),nettyProperties.getTcpPort()).sync();
        f.channel().closeFuture().sync();*/
        return bootstrap;
    }
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup group() {
        return new NioEventLoopGroup(clienteProperties.getBossCount());
    }

    @Bean
    public InetSocketAddress hostSocketAddress() throws UnknownHostException {
        return new InetSocketAddress(clienteProperties.getHost(), clienteProperties.getTcpPort());
    }
}
