package com.github.beguy.module6.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
//    @Override
//    List<Client> findAll();
//
//    List<Client> findAll(Map<String, String> filters);
//
//    Client update(Client client);
}
