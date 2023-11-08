package com.example.payment.service.impl;

import com.example.payment.domain.model.Account;
import com.example.payment.dto.request.AccountRequest;
import com.example.payment.dto.response.AccountResponse;
import com.example.payment.exception.BadRequestException;
import com.example.payment.exception.ResourceNotFoundException;
import com.example.payment.repository.AccountRepository;
import com.example.payment.service.AccountNumberGenerator;
import com.example.payment.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class DefaultAccountService implements AccountService {

  private final AccountNumberGenerator accountNumberGenerator;
  private final AccountRepository accountRepository;

  public DefaultAccountService(@Qualifier("delegatingAccountNumberGenerator")
                                 AccountNumberGenerator accountNumberGenerator,
                                 AccountRepository accountRepository) {
    this.accountNumberGenerator = accountNumberGenerator;
    this.accountRepository = accountRepository;
  }

  @Override
  public AccountResponse create(AccountRequest request) {
    Account account = from(request);
    account = accountRepository.save(account);

    return toResponse(account);
  }

  @Override
  @Transactional
  public boolean pay(String accountNumber, Double amount) {
    Account account = accountRepository.findByAccountNumber(accountNumber)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("account number %s not found", accountNumber)));

    if(account.getBalance() < amount) {
      throw new BadRequestException(String.format("Insufficient balance(%s), amount(%s)", account.getBalance(), amount));
    }

    account.setBalance(account.getBalance() - amount);

    return true;
  }

  @Override
  @Transactional
  public boolean deposit(String accountNumber, Double amount) {
    Account account = accountRepository.findByAccountNumber(accountNumber)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("Account number %s not found", accountNumber)));

    account.setBalance(account.getBalance() + amount);

    return true;
  }

  @Override
  public AccountResponse findByAccountNumber(String accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber)
      .map(this::toResponse)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("Account number %s not found", accountNumber)));
  }

  private AccountResponse toResponse(Account account) {
    AccountResponse response = new AccountResponse();
    BeanUtils.copyProperties(account, response);

    return response;
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
