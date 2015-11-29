package com.example;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class simpleService {
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(simpleService.class, args);
    }

}
