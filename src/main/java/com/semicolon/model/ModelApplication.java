package com.semicolon.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ModelApplication {

	@Bean  // this enables hashing of password
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {

		SpringApplication.run(ModelApplication.class, args);
	}

}
