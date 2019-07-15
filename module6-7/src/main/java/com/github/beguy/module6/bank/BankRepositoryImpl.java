package com.github.beguy.module6.bank;

import com.github.beguy.module6.core.entity.JpaCrudRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BankRepositoryImpl extends JpaCrudRepositoryImpl<Bank, Long> implements BankRepository {
    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
