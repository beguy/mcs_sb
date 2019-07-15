package com.github.beguy.module6.accountType;

import com.github.beguy.module6.bank.Bank;

public class AccountTypeInfoDto {
    private final long id;
    private final String name;
    private final Bank bank;

    private final Long clientsAmount;

    AccountTypeInfoDto(AccountType accountType, Long clientsAmount){
        this.id = accountType.getId();
        this.name = accountType.getName();
        this.bank = accountType.getBank();

        this.clientsAmount = clientsAmount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bank getBank() {
        return bank;
    }

    public Long getClientsAmount() {
        return clientsAmount;
    }
}
