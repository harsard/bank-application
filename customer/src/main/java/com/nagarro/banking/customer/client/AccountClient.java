package com.nagarro.banking.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service")
public interface AccountClient {
    @DeleteMapping("/accounts/by-customer/{customerId}")
    void deleteAccountForCustomer(@PathVariable Long customerId);
}
