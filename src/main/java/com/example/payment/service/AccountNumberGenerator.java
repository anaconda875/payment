package com.example.payment.service;

import com.example.payment.domain.model.Account;

import java.util.List;
import java.util.Map;

public interface AccountNumberGenerator {

  String generate(Account.Vendor vendor, Map<String, Object> metadata);
  List<Account.Vendor> supportedVendors();

}
