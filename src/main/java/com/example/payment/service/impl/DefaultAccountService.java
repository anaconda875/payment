package com.example.payment.service.impl;

import com.example.payment.domain.model.Account;
import com.example.payment.dto.request.AccountRequest;
import com.example.payment.dto.response.AccountResponse;
import com.example.payment.service.AccountNumberGenerator;
import com.example.payment.service.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DefaultAccountService implements AccountService {

  private final AccountNumberGenerator accountNumberGenerator;

  public DefaultAccountService(@Qualifier("delegatingAccountNumberGenerator")
                                 AccountNumberGenerator accountNumberGenerator) {
    this.accountNumberGenerator = accountNumberGenerator;
  }

  @Override
  public AccountResponse create(AccountRequest request) {
    Account account = from(request);
    return null;
  }

  private Account from(AccountRequest request) {
    Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = jwt.getSubject();
    Account account = new Account();
    account.setBalance(request.getBalance());
    account.setVendor(request.getVendor());
    account.setUserId(userId);

//    if(request.getVendor() == Account.Vendor.ACB) {
//      accountNumber = genIdOfAcb();
//    } else if(request.getVendor() == Account.Vendor.TPB) {
//      accountNumber = genIdOfTpb();
//    } else if() {
//
//    }
//    Map<Account.Vendor, String> map = Map.of(Account.Vendor.ACB, "%s %s %s");

    HashMap<String, Object> metadata = new HashMap<>();
    metadata.put("PROVINCE", request.getProvince());
    metadata.put("DISTRICT", request.getDistrict());
    metadata.put("BRANCH", request.getBranch());
    metadata.put("USERID", userId);

    String accountNumber = accountNumberGenerator.generate(request.getVendor(), metadata);
    account.setAccountNumber(accountNumber);

    return account;
  }

}
