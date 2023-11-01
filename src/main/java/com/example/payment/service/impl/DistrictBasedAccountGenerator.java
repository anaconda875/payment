package com.example.payment.service.impl;

import com.example.payment.domain.dto.BankInfoMapping;
import com.example.payment.domain.model.Account;
import com.example.payment.service.AbstractAccountPartGenerator;

import java.util.Map;

public class DistrictBasedAccountGenerator extends AbstractAccountPartGenerator {

  public DistrictBasedAccountGenerator(BankInfoMapping bankInfoMapping) {
    super(bankInfoMapping);
  }

  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    Map<String, Object> bi = (Map<String, Object>) bankInfoMapping.getBanks().get(vendor.name());
    Map<String, Object> districts = (Map<String, Object>) bi.get("districts");
    String districtCode = districts.get(metadata.get("DISTRICT")).toString();
    return districtCode + (next == null ? "" : ("-" + next.generate(vendor, metadata)));
  }


}
