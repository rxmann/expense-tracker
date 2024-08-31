package com.codex;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



// @SpringBootApplication: Configure the app as Spring Boot managed
// combination of 3 annotations: @EnableAutoConfiguration, @ComponentScan, @SpringBootConfiguration
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        System.out.println("Hello World!");
    }
}
