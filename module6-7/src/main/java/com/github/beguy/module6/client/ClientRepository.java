package com.github.beguy.module6.client;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    List<Client> findAll();

    List<Client> findAll(Map<String, String> filters);

    Client update(Client client);
}
