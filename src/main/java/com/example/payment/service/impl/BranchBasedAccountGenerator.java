package com.example.payment.service.impl;

import com.example.payment.domain.dto.BankInfoMapping;
import com.example.payment.domain.model.Account;
import com.example.payment.service.AbstractAccountPartGenerator;

import java.util.Map;

public class BranchBasedAccountGenerator extends AbstractAccountPartGenerator {

  public BranchBasedAccountGenerator(BankInfoMapping bankInfoMapping) {
    super(bankInfoMapping);
  }

  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    Map<String, Object> bi = (Map<String, Object>) bankInfoMapping.getBanks().get(vendor.name());
    Map<String, Object> branchs = (Map<String, Object>) bi.get("branchs");
    String branchCode = branchs.get(metadata.get("BRANCH")).toString();
    return branchCode + (next == null ? "" : ("-" + next.generate(vendor, metadata)));
  }


}
