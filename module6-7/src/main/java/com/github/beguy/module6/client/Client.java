package com.github.beguy.module6.client;

import com.github.beguy.module6.accountType.AccountType;
import com.github.beguy.module6.core.DomainObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity
public class Client extends DomainObject {
    @ManyToOne
    @NotNull
    private AccountType accountType;

    public Client() {
    }

    public Client(String name) {
        super(name);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "\n\t\t{", "\n\t\t}")
                .add(super.toString())
                .add("accountType=" + accountType.getName())
                .toString();
    }
}
