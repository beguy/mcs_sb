package com.github.beguy.module6.bank;

import com.github.beguy.module6.accountType.AccountType;
import com.github.beguy.module6.core.DomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class Bank extends DomainObject {
    @OneToMany(mappedBy = "bank", cascade = CascadeType.PERSIST)
    private List<AccountType> accounts = new ArrayList<>();

    public Bank() {
    }

    public Bank(String name) {
        super(name);
    }

    public List<AccountType> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountType> accounts) {
        accounts.stream().forEach(accountType -> accountType.setBank(this));
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "\n{", "\n}")
                .add(super.toString())
                .add("accounts=" + accounts)
                .toString();
    }
}
