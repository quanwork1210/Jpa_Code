package com.jpa.jpaCode.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    @Autowired
    private CustomSpringEventPublisher customSpringEventPublisher;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            customSpringEventPublisher.publishCustomEvent("Luu Quan");
        };
    }
}
