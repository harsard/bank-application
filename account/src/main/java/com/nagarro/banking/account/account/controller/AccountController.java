package com.nagarro.banking.account.account.controller;

import com.nagarro.banking.account.account.dto.AccountDto;
import com.nagarro.banking.account.account.dto.TransactionRequest;
import com.nagarro.banking.account.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/deposit")
    public ResponseEntity<AccountDto> deposit(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(service.deposit(request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountDto> withdraw(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(service.withdraw(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccount(id));
    }

    @DeleteMapping("/by-customer/{customerId}")
    public void deleteByCustomerId(@PathVariable Long customerId) {
        service.deleteByCustomerId(customerId);
    }
}
