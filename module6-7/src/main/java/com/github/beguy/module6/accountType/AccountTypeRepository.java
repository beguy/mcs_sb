package com.github.beguy.module6.accountType;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
    @Override
    List<AccountType> findAll();

    List<Long> clientsAmount();

    AccountType update(AccountType accountType);
}
