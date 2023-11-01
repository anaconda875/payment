package com.example.payment.service.impl;

import com.example.payment.domain.dto.BankInfoMapping;
import com.example.payment.domain.model.Account;
import com.example.payment.service.AccountNumberGenerator;
import com.example.payment.service.AccountPartGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@EnableConfigurationProperties(value = BankInfoMapping.class)
public class TpbAccountNumberGenerator implements AccountNumberGenerator {

  private final AccountPartGenerator generator;

  public TpbAccountNumberGenerator(BankInfoMapping bankInfoMapping) {
    ProvinceBasedAccountGenerator provinceGenerator = new ProvinceBasedAccountGenerator(bankInfoMapping);
    DistrictBasedAccountGenerator districtGenerator = new DistrictBasedAccountGenerator(bankInfoMapping);
    BranchBasedAccountGenerator branchGenerator = new BranchBasedAccountGenerator(bankInfoMapping);
    UserBasedAccountGenerator userGenerator = new UserBasedAccountGenerator(bankInfoMapping);

    provinceGenerator.setNext(districtGenerator);
    districtGenerator.setNext(branchGenerator);
    branchGenerator.setNext(userGenerator);

    generator = provinceGenerator;
  }

  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    log.info("TpbAccountNumberGenerator");
    return generator.generate(vendor, metadata);
  }

  @Override
  public List<Account.Vendor> supportedVendors() {
    return List.of(Account.Vendor.TPB);
  }

}
