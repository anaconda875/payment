package com.example.payment.service.impl;

import com.example.payment.domain.model.Account;
import com.example.payment.service.AccountNumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AcbAccountNumberGenerator implements AccountNumberGenerator {
  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    log.info("AcbAccountNumberGenerator");
    return UUID.randomUUID().toString();
  }

  @Override
  public List<Account.Vendor> supportedVendors() {
    return List.of(Account.Vendor.ACB);
  }
}
