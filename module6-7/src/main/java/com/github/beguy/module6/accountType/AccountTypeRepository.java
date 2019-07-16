package com.github.beguy.module6.accountType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    @Query(value = "select new com.github.beguy.module6.accountType.AccountTypeInfoDto(a.id, a.name, a.bank.id, a.bank.name, count(c.id)) " +
            "from AccountType a left join Client c on a.id=c.accountType.id group by a.id")
    List<AccountTypeInfoDto> getAllInfo();
}
