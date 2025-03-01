package com.nagarro.banking.account.account.service;

import com.nagarro.banking.account.account.dto.AccountDTO;
import com.nagarro.banking.account.account.entity.Account;
import com.nagarro.banking.account.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountDTO addMoney(Long customerId, Double amount) {
        Account account = repository.findByCustomerId(customerId).stream().findFirst()
                .orElse(new Account(null, customerId, 0.0));
        account.setBalance(account.getBalance() + amount);
        return toDTO(repository.save(account));
    }

    public AccountDTO withdrawMoney(Long customerId, Double amount) {
        Account account = repository.findByCustomerId(customerId).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) throw new RuntimeException("Insufficient balance");
        account.setBalance(account.getBalance() - amount);
        return toDTO(repository.save(account));
    }

    public List<AccountDTO> getAccountsByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId).stream().map(this::toDTO).toList();
    }

    public void deleteAccountsByCustomer(Long customerId) {
        repository.deleteByCustomerId(customerId);
    }

    private AccountDTO toDTO(Account account) {
        return new AccountDTO(account.getId(), account.getCustomerId(), account.getBalance());
    }
}
