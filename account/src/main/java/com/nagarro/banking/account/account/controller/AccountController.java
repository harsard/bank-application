package com.nagarro.banking.account.account.controller;

import com.nagarro.banking.account.account.dto.AccountDTO;
import com.nagarro.banking.account.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/add-money")
    public AccountDTO addMoney(@RequestParam Long customerId, @RequestParam Double amount) {
        return service.addMoney(customerId, amount);
    }

    @PostMapping("/withdraw-money")
    public AccountDTO withdrawMoney(@RequestParam Long customerId, @RequestParam Double amount) {
        return service.withdrawMoney(customerId, amount);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<AccountDTO> getAccounts(@PathVariable Long customerId) {
        return service.getAccountsByCustomer(customerId);
    }

    @DeleteMapping("/by-customer/{customerId}")
    public void deleteAccounts(@PathVariable Long customerId) {
        service.deleteAccountsByCustomer(customerId);
    }
}
