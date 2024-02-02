package com.ll.jum2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Jum2Application {

    public static void main(String[] args) {
        SpringApplication.run(Jum2Application.class, args);
    }

}
