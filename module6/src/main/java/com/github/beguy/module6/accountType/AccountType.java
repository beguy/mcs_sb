package com.github.beguy.module6.accountType;

import com.github.beguy.module6.bank.Bank;
import com.github.beguy.module6.client.Client;
import com.github.beguy.module6.core.DomainObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class AccountType extends DomainObject {
    @ManyToOne
    private Bank bank;

    @OneToMany(mappedBy = "accountType",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
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
        clientEntities.stream().forEach(client -> client.setAccountType(this));
        this.clientEntities = clientEntities;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "\n\t{", "\n\t}")
                .add(super.toString())
                .add("bank=" + bank.getName())
                .add("clientEntities=" + clientEntities)
                .toString();
    }
}
