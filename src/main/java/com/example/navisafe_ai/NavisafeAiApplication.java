package com.example.navisafe_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.example.navisafe_ai", "app"})
public class NavisafeAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavisafeAiApplication.class, args);
	}

}
