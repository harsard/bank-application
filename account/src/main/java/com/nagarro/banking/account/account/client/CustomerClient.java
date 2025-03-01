package com.nagarro.banking.account.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",  path = "/api/v1/customers")
public interface CustomerClient {
    @GetMapping("/customers/{id}")
    void validateCustomer(@PathVariable Long id);
}

