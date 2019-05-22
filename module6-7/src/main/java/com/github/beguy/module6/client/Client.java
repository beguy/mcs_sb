package com.github.beguy.module6.client;

import com.github.beguy.module6.accountType.AccountType;
import com.github.beguy.module6.core.DomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "CLIENTS")
public class Client extends DomainObject {
    @ManyToOne
    @NotNull
    private AccountType accountType;

    @Column(name = "DATE_ACC", columnDefinition = "DATE DEFAULT CURRENT_DATE NOT NULL ")
    private java.sql.Date accountDate;

    public Client() {
    }

    public Client(String name) {
        super(name);
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
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
                .add("accountDate=" + accountDate)
                .toString();
    }
}
