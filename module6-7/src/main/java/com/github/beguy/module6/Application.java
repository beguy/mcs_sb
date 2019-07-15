package com.github.beguy.module6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.beguy.module6")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
