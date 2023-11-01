package com.example.payment.service.impl;

import com.example.payment.domain.model.Account;
import com.example.payment.service.AccountNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("delegatingAccountNumberGenerator")
public class DelegatingAccountNumberGenerator implements AccountNumberGenerator {

  private final Map<Account.Vendor, AccountNumberGenerator> generators;

  public DelegatingAccountNumberGenerator(List<AccountNumberGenerator> generators) {
    this.generators = new HashMap<>();
    generators.forEach(el -> el.supportedVendors().forEach(v -> this.generators.put(v, el)));
  }

  @Override
  public String generate(Account.Vendor vendor, Map<String, Object> metadata) {
    return generators.get(vendor).generate(vendor, metadata);
  }

  @Override
  public List<Account.Vendor> supportedVendors() {
    return new ArrayList<>(generators.keySet());
  }

}
