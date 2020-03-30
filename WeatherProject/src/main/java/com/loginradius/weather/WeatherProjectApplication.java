package com.loginradius.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class WeatherProjectApplication {

	/* Program that needs to be run */
	public static void main(String[] args) {
		SpringApplication.run(WeatherProjectApplication.class, args);
	}

}
