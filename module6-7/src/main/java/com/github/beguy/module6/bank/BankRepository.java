package com.github.beguy.module6.bank;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankRepository extends CrudRepository<Bank, Long> {
    @Override
    List<Bank> findAll();

    Bank update(Bank bank);
}
