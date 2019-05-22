package com.github.beguy.module6.accountType;

public class AccountTypeInfoDto {
    private final AccountType accountType;
    private final Integer clientsAmount;

    AccountTypeInfoDto(AccountType accountType, Integer clientsAmount){
        this.accountType = accountType;
        this.clientsAmount = clientsAmount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Integer getClientsAmount() {
        return clientsAmount;
    }
}
