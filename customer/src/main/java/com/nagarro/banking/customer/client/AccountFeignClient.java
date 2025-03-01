package com.nagarro.banking.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", path = "/api/v1/accounts")
public interface AccountFeignClient {

    @DeleteMapping("/by-customer/{customerId}")
    void deleteAccountsByCustomer(@PathVariable("customerId") Long customerId);
}

