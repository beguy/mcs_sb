package com.github.beguy.module6.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a where dateadd(day, a.serviceFeePeriod, a.lastFeeChargeDate) <= :serviceDate")
    List<Account> findAllByServiceFeeDate(@Param("serviceDate") Date serviceDate);
}
