package com.nagarro.banking.account.account.service;

import com.nagarro.banking.account.account.client.CustomerClient;
import com.nagarro.banking.account.account.dto.AccountDTO;
import com.nagarro.banking.account.account.entity.Account;
import com.nagarro.banking.account.account.repository.AccountRepository;
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
        customerClient.validateCustomer(customerId);

        Account account = repository.findByCustomerId(customerId).stream().findFirst()
                .orElse(new Account(null, customerId, 0.0));

        account.setBalance(account.getBalance() + amount);
        return toDTO(repository.save(account));
    }

    @Transactional
    public AccountDTO withdrawMoney(Long customerId, Double amount) {
        customerClient.validateCustomer(customerId);  // ✅ Validate Customer Exists

        Account account = repository.findByCustomerId(customerId).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        return toDTO(repository.save(account));
    }

    public List<AccountDTO> getAccountsByCustomer(Long customerId) {
        customerClient.validateCustomer(customerId);  // ✅ Validate Customer Exists
        return repository.findByCustomerId(customerId).stream().map(this::toDTO).toList();
    }

    public void deleteAccountsByCustomer(Long customerId) {
        customerClient.validateCustomer(customerId);  // ✅ Validate Customer Exists
        repository.deleteByCustomerId(customerId);
    }

    private AccountDTO toDTO(Account account) {
        return new AccountDTO(account.getId(), account.getCustomerId(), account.getBalance());
    }
}
