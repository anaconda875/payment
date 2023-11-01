package com.example.payment.service.impl;

import com.example.payment.domain.dto.BankInfoMapping;
import com.example.payment.domain.model.Account;
import com.example.payment.service.AbstractAccountPartGenerator;

import java.util.Map;

public class ProvinceBasedAccountGenerator extends AbstractAccountPartGenerator {

  public ProvinceBasedAccountGenerator(BankInfoMapping bankInfoMapping) {
    super(bankInfoMapping);
  }

  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    Map<String, Object> bi = (Map<String, Object>) bankInfoMapping.getBanks().get(vendor.name());
    Map<String, Object> provinces = (Map<String, Object>) bi.get("provinces");
    String provinceCode = provinces.get(metadata.get("PROVINCE")).toString();
    return provinceCode + (next == null ? "" : ("-" + next.generate(vendor, metadata)));
  }

}
