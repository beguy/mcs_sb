package com.github.beguy.module6.accountType;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountTypeDao {
    @PersistenceContext
    private EntityManager em;

    public void save(AccountType accountType) {
        em.persist(accountType);
    }

    public void addAccounTypeByName(String name) {
        AccountType accountType = new AccountType(name);
        em.persist(accountType);
    }

    public void delete(AccountType accountType) {
        em.remove(accountType);
    }

    public void delete(long id) {
        em.remove(em.find(AccountType.class, id));
    }

    public void update(AccountType accountType) {
        em.merge(accountType);
    }

    public AccountType findById(long id) {
        return em.find(AccountType.class, id);
    }

    public List<AccountType> findAll() {
        Query query = em.createQuery(
                "from AccountType c");
        return query.getResultList();
    }

    public List<Long> clientsAmount() {
        Query query = em.createQuery("select count(c.id) from AccountType a left join Client c on a.id=c.accountType group by a.id");
        return query.getResultList();
    }
}
