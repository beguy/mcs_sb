package com.github.beguy.module6.account;

import java.util.Date;
import java.util.List;

public interface AccountCustomRepository {
    List<Account> findAllByServiceFeeDate(Date endDate);
}
