package com.example.payment.dto.response;

import com.example.payment.domain.model.Account;
import lombok.Data;

@Data
public class AccountResponse {
  private Long id;
  private String accountNumber;
  private Double balance;
  private String userId;
  private Account.Vendor vendor;
}
