package com.example.payment.service;

import com.example.payment.domain.dto.BankInfoMapping;

public abstract class AbstractAccountPartGenerator implements AccountPartGenerator {

  protected final BankInfoMapping bankInfoMapping;

  protected AccountPartGenerator next;

  protected AbstractAccountPartGenerator(BankInfoMapping bankInfoMapping) {
    this.bankInfoMapping = bankInfoMapping;
  }

  @Override
  public void setNext(AccountPartGenerator next) {
    this.next = next;
  }

}
