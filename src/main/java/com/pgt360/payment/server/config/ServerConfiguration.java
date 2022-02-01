package com.pgt360.payment.server.config;

import com.pgt360.payment.client.config.ClienteInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ServerProperties.class)
public class ServerConfiguration {
    private final ServerProperties serverProperties;
    private ServerBootstrap serverBootstrap;
    private final NioEventLoopGroup bossLoopGroup;
    private final NioEventLoopGroup workerLoopGroup;
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossLoopGroup, workerLoopGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childHandler(new ServerInitializer());
        return serverBootstrap;
    }
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossLoopGroup() {
        return new NioEventLoopGroup(1);
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerLoopGroup() {
        return new NioEventLoopGroup(10);
    }
    @Bean
    public InetSocketAddress tcpSocketAddress() {
        return new InetSocketAddress(serverProperties.getPort());
    }
}
