package com.github.beguy.module6.accountType;

public class AccountTypeInfoDto {
    private final AccountType accountType;
    private final Long clientsAmount;

    AccountTypeInfoDto(AccountType accountType, Long clientsAmount){
        this.accountType = accountType;
        this.clientsAmount = clientsAmount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Long getClientsAmount() {
        return clientsAmount;
    }
}
