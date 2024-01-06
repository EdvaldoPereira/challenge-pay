package br.com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "br.com.challenge")
public class ChallengePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengePayApplication.class, args);
	}

}
