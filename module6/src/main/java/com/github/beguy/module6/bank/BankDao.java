package com.github.beguy.module6.bank;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BankDao {
    @PersistenceContext
    private EntityManager em;

    public void save(Bank bank) {
        em.persist(bank);
    }

    public void addBankByName(String name) {
        Bank bank = new Bank(name);
        em.persist(bank);
    }

    public void delete(Bank bank) {
        em.remove(bank);
    }

    public void delete(long id) {
        em.createQuery("delete from Bank c where c.id=:id").setParameter("id", id).executeUpdate();
    }

    public List<Bank> findAll() {
        Query query = em.createQuery(
                "from Bank c");
        return query.getResultList();
    }
}
