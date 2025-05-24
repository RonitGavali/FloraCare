package com.project.Flora_Care;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class FloraCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(FloraCareApplication.class, args);
	log.info("A FLORA CARE APPLICATION IS STARTED . . . . . . . . . . . . . . .");
	}

}
