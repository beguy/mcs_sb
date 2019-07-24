package com.github.beguy.module6.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService thiz;

    @Override
    //(second minute hour day month weekday)
    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void chargeAllServiceFeeForToday() {
        Date today = new Date();
        thiz.chargeAllServiceFeeForDate(today);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void chargeAllServiceFeeForDate(Date date) {
        List<Account> allByServiceFeeDate = accountRepository.findAllByServiceFeeDate(date);
        allByServiceFeeDate.forEach(account -> thiz.chargeServiceFee(account));
        accountRepository.saveAll(allByServiceFeeDate);
    }

    public void chargeServiceFee(Account account){
        BigDecimal currentAccount = account.getValue();
        // current = current - current * (percent/100)
        //                     /\service fee/\
        BigDecimal serviceFee = currentAccount.multiply(account.getServiceFeePercent().divide(new BigDecimal(100)));
        currentAccount = currentAccount.subtract(serviceFee);
        BigDecimal minimalAccountForServiceFee = account.getMinimalAccountForServiceFee();
        // current > minimal
        if (currentAccount.compareTo(minimalAccountForServiceFee) > 0)
            account.setValue(currentAccount);
    }
}
