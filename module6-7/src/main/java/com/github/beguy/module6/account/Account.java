package com.github.beguy.module6.account;

import com.github.beguy.module6.client.Client;
import com.github.beguy.module6.core.entity.DomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT")
public class Account extends DomainObject {
    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE NOT NULL ")
    private Date accountDate;
    @Column(name = "LAST_FEE_CHARGE_DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE NOT NULL ")
    private Date lastFeeChargeDate;

    @Column(name = "SERVICE_PERCENT")
    @Digits(integer = 3, fraction = 1)
    private BigDecimal serviceFeePercent;
    @Column(name = "SERVICE_PERIOD")
    private long serviceFeePeriod;
    @Column(name = "MINIMAL_ACCOUNT_FOR_SERVICE_FEE")
    private BigDecimal minimalAccountForServiceFee;
    @OneToOne(mappedBy = "account")
    private Client client;

    public Date getLastFeeChargeDate() {
        return lastFeeChargeDate;
    }

    public void setLastFeeChargeDate(Date lastFeeChargeDate) {
        this.lastFeeChargeDate = lastFeeChargeDate;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        setLastFeeChargeDate(accountDate); // reset fee charge date
        this.accountDate = accountDate;
    }

    public BigDecimal getMinimalAccountForServiceFee() {
        return minimalAccountForServiceFee;
    }

    public void setMinimalAccountForServiceFee(BigDecimal minimalAccountForServiceFee) {
        this.minimalAccountForServiceFee = minimalAccountForServiceFee;
    }

    public BigDecimal getServiceFeePercent() {
        return serviceFeePercent;
    }

    public void setServiceFeePercent(BigDecimal serviceFeePercent) {
        this.serviceFeePercent = serviceFeePercent;
    }

    public long getServiceFeePeriod() {
        return serviceFeePeriod;
    }

    public void setServiceFeePeriod(long serviceFeePeriod) {
        this.serviceFeePeriod = serviceFeePeriod;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;

    }
}
