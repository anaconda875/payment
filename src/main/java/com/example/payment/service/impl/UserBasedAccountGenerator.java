package com.example.payment.service.impl;

import com.example.payment.domain.dto.BankInfoMapping;
import com.example.payment.domain.model.Account;
import com.example.payment.service.AbstractAccountPartGenerator;

import java.util.Map;

public class UserBasedAccountGenerator extends AbstractAccountPartGenerator {

  public UserBasedAccountGenerator(BankInfoMapping bankInfoMapping) {
    super(bankInfoMapping);
  }

  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    String userid = String.valueOf(metadata.get("USERID").hashCode());
    return userid + (next == null ? "" : ("-" + next.generate(vendor, metadata)));
  }

}
