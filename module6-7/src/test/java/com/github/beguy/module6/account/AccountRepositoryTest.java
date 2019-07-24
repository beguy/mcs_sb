package com.github.beguy.module6.account;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findAllByServiceFeeDate() {
        // given
        Date startedDate = new Date(2018, 11, 25);
        Date currentDate = new Date(2018, 11, 27);
        Account accountBeforeDate = new Account();
        accountBeforeDate.setServiceFeePeriod(1);

        Account accountInDate = new Account();
        accountInDate.setServiceFeePeriod(2);

        Account accountAfterDate = new Account();
        accountAfterDate.setServiceFeePeriod(4);

        Account anotherAccountAfterDate = new Account();
        anotherAccountAfterDate.setServiceFeePeriod(365);

        List<Account> accounts = Arrays.asList(accountBeforeDate, accountInDate, accountAfterDate, anotherAccountAfterDate);
        accounts.forEach(account -> account.setAccountDate(startedDate));
        accountRepository.saveAll(accounts);
        List<Account> finded = accountRepository.findAllByServiceFeeDate(currentDate);
        assertThat(finded, is(Arrays.asList(accountBeforeDate, accountInDate)));
    }
}