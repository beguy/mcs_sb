package com.github.beguy.module6.client;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientDao {
    @PersistenceContext
    private EntityManager em;

    public long save(Client client) {
        em.persist(client);
        return client.getId();
    }

    public Client find(long id) {
        return em.find(Client.class, id);
    }

    public void delete(Client client) {
        em.remove(client);
    }

    public void delete(long id) {
        em.remove(em.find(Client.class, id));
    }

    public void update(Client client) {
        em.merge(client);
    }

    public Client findById(long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findAll() {
        Query query = em.createQuery(
                "from Client c");
        return query.getResultList();
    }
}
