package com.github.beguy.module6.accountType;

import com.github.beguy.module6.bank.Bank;
import com.github.beguy.module6.client.Client;
import com.github.beguy.module6.core.entity.NamedDomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType extends NamedDomainObject {
    @ManyToOne
    @JoinColumn(name = "BANK_ID")
    @NotNull
    private Bank bank;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.REMOVE)
    private List<Client> clientEntities = new ArrayList<>();

    public AccountType() {
    }

    public AccountType(String name) {
        super(name);
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Client> getClientEntities() {
        return clientEntities;
    }

    public void setClientEntities(List<Client> clientEntities) {
        clientEntities.forEach(client -> client.setAccountType(this));
        this.clientEntities = clientEntities;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "{", "}")
                .add(super.toString())
                .add("clientEntities=" + clientEntities)
                .toString();
    }
}
