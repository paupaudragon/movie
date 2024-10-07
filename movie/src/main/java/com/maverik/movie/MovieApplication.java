package com.maverik.movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) throws UnsupportedEncodingException {

		SpringApplication.run(MovieApplication.class, args);
	}

}
