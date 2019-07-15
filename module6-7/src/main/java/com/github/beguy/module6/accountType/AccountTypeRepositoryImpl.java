package com.github.beguy.module6.accountType;

import com.github.beguy.module6.core.entity.JpaCrudRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountTypeRepositoryImpl extends JpaCrudRepositoryImpl<AccountType, Long> implements AccountTypeRepository {
    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Long> clientsAmount() {
        Query query = entityManager.createQuery("select count(c.id) from AccountType a left join Client c on a.id=c.accountType group by a.id");
        return query.getResultList();
    }
}
