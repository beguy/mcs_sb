package com.github.beguy.module6.client;

import com.github.beguy.module6.account.Account;
import com.github.beguy.module6.accountType.AccountType;
import com.github.beguy.module6.core.entity.NamedDomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity
@Table(name = "CLIENTS")
public class Client extends NamedDomainObject {
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    @NotNull
    private AccountType accountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    private Account account;

    public Client() {
    }

    public Client(String name) {
        super(name);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "{", "}")
                .add(super.toString())
                .toString();
    }
}
