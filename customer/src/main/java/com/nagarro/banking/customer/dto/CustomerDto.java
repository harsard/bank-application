package com.nagarro.banking.customer.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String customerEmail;
    private String customerName;
    private String customerAddress;

}
