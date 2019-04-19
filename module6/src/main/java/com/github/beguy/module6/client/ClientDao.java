package com.github.beguy.module6.client;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ClientDao {
    @PersistenceContext
    EntityManager em;
}
