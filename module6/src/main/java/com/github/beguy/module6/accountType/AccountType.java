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
    private List<Client> clients = new ArrayList<>();

    public AccountType(String name) {
        super(name);
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        clients.stream().forEach(client -> client.setAccountType(this));
        this.clients = clients;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "\n\t{", "\n\t}")
                .add(super.toString())
                .add("bank=" + bank.getName())
                .add("clients=" + clients)
                .toString();
    }
}
