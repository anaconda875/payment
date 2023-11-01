package com.example.payment.service;

import com.example.payment.domain.model.Account;

import java.util.Map;

public interface AccountPartGenerator {

  String generate(Account.Vendor vendor, Map<String, Object> metadata);
  void setNext(AccountPartGenerator next);

}
