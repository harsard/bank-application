package com.nagarro.banking.customer.controller;

import com.nagarro.banking.customer.config.AppConfig;
import com.nagarro.banking.customer.dto.CustomerDTO;
import com.nagarro.banking.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@RefreshScope
public class CustomerController {

    private final CustomerService service;
    private final AppConfig appConfig;



    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("/message")
    public String getMessage() {
        return appConfig.getMessage();
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
