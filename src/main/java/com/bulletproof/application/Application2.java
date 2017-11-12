package com.bulletproof.application;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.bulletproof")
@Configuration
@ComponentScan({ "com.bulletproof.service", "com.bulletproof.repository", "com.bulletproof" })
@EnableJpaRepositories({ "com.bulletproof.service", "com.bulletproof.repository", "com.bulletproof.resource",
	"com.bulletproof.model" })
@EntityScan("com.bulletproof.model")
@EnableAutoConfiguration
public class Application2 {

    // store the parsed csv data for quick retrieval.
    // public static JsonArray jsonCustomer;

    public static void main(String[] args) throws FileNotFoundException, IOException {

	// start springboot application.
	SpringApplication.run(Application2.class, args);
    }

}
