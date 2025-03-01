package com.nagarro.banking.account.account.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private Long customerId;
    private Double balance;
}
