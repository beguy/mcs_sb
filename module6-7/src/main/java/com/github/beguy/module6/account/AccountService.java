package com.github.beguy.module6.account;

import java.util.Date;

public interface AccountService {
    void chargeAllServiceFeeForToday();
    void chargeAllServiceFeeForDate(Date endDate);
    void chargeServiceFee(Account account);
}
