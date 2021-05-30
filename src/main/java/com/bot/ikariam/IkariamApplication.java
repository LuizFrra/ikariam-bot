package com.bot.ikariam;

import com.bot.ikariam.configuration.Properties;
import com.bot.ikariam.handler.connection.ConnectionHandler;
import com.bot.ikariam.handler.fortress.FortressHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class IkariamApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkariamApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ConnectionHandler connectionHandler, FortressHandler fortressHandler) {
		return (args) -> {
			while (true) {
				try {
					connectionHandler.execute();
					fortressHandler.captureContrabandist();
					Thread.sleep(ThreadLocalRandom.current().nextInt(3000) + 5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
}
