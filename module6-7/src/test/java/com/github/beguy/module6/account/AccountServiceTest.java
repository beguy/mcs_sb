package com.github.beguy.module6.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService = new AccountServiceImpl();


    @Test
    public void chargeAllServiceFeeForDate() {
        Date lastFeeDate = new Date(2018, 11, 25);
        Date feeStartedDate = new Date(2018, 11, 27);

        Account accountBeforeDate = new Account();
        accountBeforeDate.setLastFeeChargeDate(lastFeeDate);
        accountBeforeDate.setValue(new BigDecimal(10_000));
        accountBeforeDate.setMinimalValueForServiceFee(new BigDecimal(500));
        accountBeforeDate.setServiceFeePercent(new BigDecimal(5));
        // 10_000 - 10_000 * 5% = 9500
        BigDecimal accountBeforeDateExpectedValue = new BigDecimal(9500);

        Account accountInDate = new Account();
        accountInDate.setLastFeeChargeDate(lastFeeDate);
        accountInDate.setValue(new BigDecimal(100_000));
        accountInDate.setMinimalValueForServiceFee(new BigDecimal(1000));
        accountInDate.setServiceFeePercent(new BigDecimal("15.55"));
        // 100_000 - 100_000 * 15.55% = 84_450
        BigDecimal accountInDateExpectedValue = new BigDecimal(84_450);

        Account accountInDateWithMinimalValue = new Account();
        accountInDateWithMinimalValue.setLastFeeChargeDate(lastFeeDate);
        accountInDateWithMinimalValue.setValue(new BigDecimal(100));
        accountInDateWithMinimalValue.setMinimalValueForServiceFee(new BigDecimal(80));
        accountInDateWithMinimalValue.setServiceFeePercent(new BigDecimal(50));
        // 100 - 100 * 50% = 50 < 80 (minimal) => no change == 100
        BigDecimal accountInDateWithMinimalValueExpectedValue = new BigDecimal(100);

        List<Account> accounts = Arrays.asList(accountBeforeDate, accountInDate, accountInDateWithMinimalValue);

        when(accountRepository.findAllByServiceFeeDate(any(Date.class))).thenReturn(accounts);
        accountService.chargeAllServiceFeeForDate(feeStartedDate);

        accounts.stream()
                .map(Account::getLastFeeChargeDate)
                .forEach(date -> assertEquals("Last fee charge date should change", feeStartedDate, date));

        assertTrue("The account should be reduced by the service fee",
                accountBeforeDate.getValue().compareTo(accountBeforeDateExpectedValue) == 0 &&
                        accountInDate.getValue().compareTo(accountInDateExpectedValue) == 0
        );
        assertTrue("The minimal account should not be reduced by the service fee", accountInDateWithMinimalValue.getValue()
                .compareTo(accountInDateWithMinimalValueExpectedValue) == 0);
    }

    @Test
    public void chargeServiceFee() {
        Account accountWithMinimalValue = new Account();
        accountWithMinimalValue.setValue(new BigDecimal(100));
        accountWithMinimalValue.setMinimalValueForServiceFee(new BigDecimal(80));
        accountWithMinimalValue.setServiceFeePercent(new BigDecimal(50));

        Account accountNormalValue = new Account();
        accountNormalValue.setValue(new BigDecimal(10_000));
        accountNormalValue.setMinimalValueForServiceFee(new BigDecimal(80));
        accountNormalValue.setServiceFeePercent(new BigDecimal("15.555"));

        accountService.chargeServiceFee(accountWithMinimalValue);
        assertEquals("The minimal account should not be reduced by the service fee\"", accountWithMinimalValue.getValue(), new BigDecimal(100));

        accountService.chargeServiceFee(accountNormalValue);
        // 10_000 - 10_000 * 15.555% = 8 444.5
        assertTrue("The account should be reduced by the service fee in first time", new BigDecimal("8444.5").compareTo(accountNormalValue.getValue()) == 0 );

        accountNormalValue.setServiceFeePercent(new BigDecimal(10));
        accountService.chargeServiceFee(accountNormalValue);
        // 8 444.5 - 8 444.5 * 10% = 7600.05
        assertTrue("The account should be reduced by the service fee in second time", (new BigDecimal("7600.05")).compareTo(accountNormalValue.getValue()) == 0 );
    }
}