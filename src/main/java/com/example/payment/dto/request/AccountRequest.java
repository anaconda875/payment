package com.example.payment.dto.request;

import com.example.payment.domain.model.Account;
import lombok.Data;

@Data
public class AccountRequest {

  private Double balance;

  private Account.Vendor vendor;

  private String province;
  private String district;
  private String branch;

}
