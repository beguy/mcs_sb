package com.github.beguy.module6.client;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheEvict
public interface ClientRepository extends JpaRepository<Client, Long> {
}
