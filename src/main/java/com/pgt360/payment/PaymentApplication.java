package com.pgt360.payment;

import com.pgt360.payment.client.Cliente;
import com.pgt360.payment.server.Server;
import io.netty.channel.ChannelFutureListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Timer;
import java.util.TimerTask;

@RequiredArgsConstructor
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	private final Server server;
	private final Cliente cliente;

	@Bean
	public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
		return new ApplicationListener<ApplicationReadyEvent>() {
			@Override
			public void onApplicationEvent(ApplicationReadyEvent event) {
				server.start().addListener((ChannelFutureListener) future -> {
					cliente.start();
				});
			}
		};
	}
}
