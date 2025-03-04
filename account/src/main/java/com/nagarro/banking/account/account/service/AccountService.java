package com.nagarro.banking.account.account.service;

import com.nagarro.banking.account.account.client.CustomerClient;
import com.nagarro.banking.account.account.dto.AccountDTO;
import com.nagarro.banking.account.account.entity.Account;
import com.nagarro.banking.account.account.exception.AccountNotFoundException;
import com.nagarro.banking.account.account.exception.CustomerNotFoundException;
import com.nagarro.banking.account.account.exception.InsufficientBalanceException;
import com.nagarro.banking.account.account.repository.AccountRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final CustomerClient customerClient;

    @Transactional
    public AccountDTO addMoney(Long customerId, Double amount) {
        validateCustomer(customerId);

        Account account = repository.findByCustomerId(customerId).stream().findFirst()
                .orElse(new Account(null, customerId, 0.0));

        account.setBalance(account.getBalance() + amount);
        return toDTO(repository.save(account));
    }

    @Transactional
    public AccountDTO withdrawMoney(Long customerId, Double amount) {
        validateCustomer(customerId);

        Account account = repository.findByCustomerId(customerId).stream().findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found for customer ID: " + customerId));

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }

        account.setBalance(account.getBalance() - amount);
        return toDTO(repository.save(account));
    }

    public List<AccountDTO> getAccountsByCustomer(Long customerId) {
        validateCustomer(customerId);
        return repository.findByCustomerId(customerId).stream().map(this::toDTO).toList();
    }

    public void deleteAccountsByCustomer(Long customerId) {
        validateCustomer(customerId);
        repository.deleteByCustomerId(customerId);
    }

    private void validateCustomer(Long customerId) {
        try {
            customerClient.validateCustomer(customerId);
        } catch (FeignException.NotFound e) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist.");
        }
    }

    private AccountDTO toDTO(Account account) {
        return new AccountDTO(account.getId(), account.getCustomerId(), account.getBalance());
    }
}