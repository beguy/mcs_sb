package com.github.beguy.module6;

import com.github.beguy.module6.accountType.AccountType;
import com.github.beguy.module6.bank.Bank;
import com.github.beguy.module6.bank.BankDao;
import com.github.beguy.module6.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    BankDao bankDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Bank bank1 = new Bank("VILLAGEBANK");
        List<AccountType> accountTypeList = Arrays.asList(new AccountType("DEBIT"),
                new AccountType("CREDIT")
        );
        Client petrov = new Client("PETROV");
        Client ivanov = new Client("IVANOV");
        Client sidorov = new Client("SIDOROV");
        //TODO: replace with AccountDAO
        accountTypeList.get(0).setClients(Arrays.asList(petrov, sidorov));
        accountTypeList.get(1).setClients(Arrays.asList(ivanov));
        bank1.setAccounts(accountTypeList);

        bankDao.addBank(bank1);
        System.out.println(bank1);
    }

}
