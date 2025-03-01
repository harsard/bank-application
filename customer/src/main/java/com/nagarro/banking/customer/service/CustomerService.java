package com.nagarro.banking.customer.service;

import com.nagarro.banking.customer.client.AccountClient;
import com.nagarro.banking.customer.dto.CustomerDto;
import com.nagarro.banking.customer.entity.Customer;
import com.nagarro.banking.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final AccountClient accountClient;

    public CustomerDto addCustomer(CustomerDto dto) {

        Customer customer = repository.save(CustomerMapper.toEntity(dto));
        return CustomerMapper.toDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        return repository.findAll().stream().map(CustomerMapper::toDto).toList();
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return CustomerMapper.toDto(customer);
    }

    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        Customer customer = repository.findById(id).orElseThrow();
        customer.setName(dto.getName());
        return CustomerMapper.toDto(repository.save(customer));
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
        accountClient.deleteAccountForCustomer(id); // Notify Account Service
    }
}
