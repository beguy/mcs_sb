package com.github.beguy.module6;

import com.github.beguy.module6.core.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({WebSecurityConfig.class})
@EnableJpaRepositories(basePackages = "com.github.beguy.module6")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
