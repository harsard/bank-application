package com.nagarro.banking.account.account.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String customerEmail;
    private String customerName;
    private String customerAddress;

}
