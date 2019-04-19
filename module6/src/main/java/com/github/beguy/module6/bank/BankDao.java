package com.github.beguy.module6.bank;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BankDao {
    @PersistenceContext
    EntityManager em;

    public void addBank(Bank bank) {
        em.persist(bank);
    }

    public void addBankByName(String name) {
        Bank bank = new Bank(name);
        em.persist(bank);
    }

    public void deleteBank(Bank bank) {
        em.remove(bank);
    }

//    void setAccountType() {
//    }
//
//    void deleteAccountType(Bank from, AccountType accountType) {
//    }
//
//    void setAccountType() {
//    }
}
