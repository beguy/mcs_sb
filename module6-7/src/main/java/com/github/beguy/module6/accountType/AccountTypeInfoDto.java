package com.github.beguy.module6.accountType;
public class AccountTypeInfoDto {
    private final long id;
    private final String name;
    private final long bankId;
    private final String bankName;
    private final Long clientsAmount;

    public AccountTypeInfoDto(long id, String name, long bankId, String bankName, Long clientsAmount) {
        this.id = id;
        this.name = name;
        this.bankId = bankId;
        this.bankName = bankName;
        this.clientsAmount = clientsAmount;
    }

    AccountTypeInfoDto(AccountType accountType, Long clientsAmount){
        this.id = accountType.getId();
        this.name = accountType.getName();
        this.bankId = accountType.getBank().getId();
        this.bankName = accountType.getBank().getName();
        this.clientsAmount = clientsAmount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public Long getClientsAmount() {
        return clientsAmount;
    }
}
