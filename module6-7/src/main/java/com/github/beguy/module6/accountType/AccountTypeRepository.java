package com.github.beguy.module6.accountType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    //    @Override
//    List<AccountType> findAll();
//    @Override
//    <S extends AccountType> List<S> findAll(Example<S> example);
    @Query(value = "select count(c.id) from AccountType a left join Client c on a.id=c.accountType.id group by a.id")
    List<Long> clientsAmount();
}
