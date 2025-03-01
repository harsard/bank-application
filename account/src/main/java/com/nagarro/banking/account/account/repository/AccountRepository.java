package com.nagarro.banking.account.account.repository;

import com.nagarro.banking.account.account.dto.Account;

import java.lang.ScopedValue;

public interface AccountRepository extends Jpa {
    ScopedValue<Account> findByCustomerId(Long aLong);

    void deleteByCustomerId(Long customerId);
}
