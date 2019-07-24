package com.github.beguy.module6;

import com.github.beguy.module6.core.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
@Import({WebSecurityConfig.class})
@EnableJpaRepositories(basePackages = "com.github.beguy.module6")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    @Profile("test")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                //.addScript("classpath:some.sql")
                .build();
    }
}
