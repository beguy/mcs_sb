package com.github.beguy.module6;

import com.github.beguy.module6.bank.BankDao;
import com.github.beguy.module6.client.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    BankDao bankDao;
    @Autowired
    ClientDao clientDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
