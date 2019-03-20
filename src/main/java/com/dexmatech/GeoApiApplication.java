package com.dexmatech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class GeoApiApplication {

	public static void main(String[] args) {
		log.info("Geo Application Started");
		SpringApplication.run(GeoApiApplication.class, args);
	}
}
