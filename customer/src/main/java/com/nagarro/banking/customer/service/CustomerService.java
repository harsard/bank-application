package com.nagarro.banking.customer.service;

import com.nagarro.banking.customer.client.AccountFeignClient;
import com.nagarro.banking.customer.dto.CustomerDTO;
import com.nagarro.banking.customer.entity.Customer;
import com.nagarro.banking.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final AccountFeignClient accountClient;

    public CustomerDTO addCustomer(CustomerDTO dto) {
        Customer customer = new Customer(null, dto.getName(), dto.getEmail());
        Customer saved = repository.save(customer);
        return toDTO(saved);
    }

    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public CustomerDTO getCustomer(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = repository.findById(id).orElseThrow();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return toDTO(repository.save(customer));
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
        accountClient.deleteAccountsByCustomer(id);
    }

    private CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail());
    }
}
