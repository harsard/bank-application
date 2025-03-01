package com.nagarro.banking.account.account.service;

import com.nagarro.banking.account.account.client.CustomerClient;
import com.nagarro.banking.account.account.dto.Account;
import com.nagarro.banking.account.account.dto.AccountDto;
import com.nagarro.banking.account.account.dto.TransactionRequest;
import com.nagarro.banking.account.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final CustomerClient customerClient;

    public AccountDto deposit(TransactionRequest request) {
        customerClient.validateCustomer(request.customerId());
        Account account = repository.findByCustomerId(request.customerId()).orElse(new Account(request.customerId()));
        account.deposit(request.amount());
        return AccountMapper.toDto(repository.save(account));
    }

    public AccountDto withdraw(TransactionRequest request) {
        customerClient.validateCustomer(request.customerId());
        Account account = repository.findByCustomerId(request.customerId()).orElseThrow();
        account.withdraw(request.amount());
        return AccountMapper.toDto(repository.save(account));
    }

    public void deleteByCustomerId(Long customerId) {
        repository.deleteByCustomerId(customerId);
    }

    public AccountDto getAccount(Long id) {

    }
}

