package com.deltabook.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
        System.out.println("It works!");
		SpringApplication.run(DemoApplication.class, args);
	}

}

