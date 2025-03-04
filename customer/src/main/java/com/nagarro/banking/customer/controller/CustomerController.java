package com.nagarro.banking.customer.controller;

import com.nagarro.banking.customer.dto.CustomerDTO;
import com.nagarro.banking.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@RefreshScope
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    @PostMapping
    public CustomerDTO add(@RequestBody CustomerDTO dto) {
        return service.addCustomer(dto);
    }

    @GetMapping
    public List<CustomerDTO> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getOne(@PathVariable Long id) {
        return service.getCustomer(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return service.updateCustomer(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCustomer(id);
    }
}
