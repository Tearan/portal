package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.web.config.EnableSpringDataWebSupport;



@ComponentScan({"com.example.demo"})
@SpringBootApplication
@EnableSpringDataWebSupport
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}


}