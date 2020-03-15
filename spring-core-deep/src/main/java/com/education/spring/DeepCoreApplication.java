package com.education.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeepCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeepCoreApplication.class, args);
    }

    @Bean
    public Startup startup() {
        return new Startup();
    }
}
