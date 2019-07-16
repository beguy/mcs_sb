package com.github.beguy.module6.bank;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
//    @Override
//    List<Bank> findAll();
//
//    Bank update(Bank bank);
}
