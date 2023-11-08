package com.example.payment.web.rest;

import com.example.payment.dto.request.AccountRequest;
import com.example.payment.dto.response.AccountResponse;
import com.example.payment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountResource {

  private final AccountService service;

  @PostMapping
  public AccountResponse create(@RequestBody AccountRequest request) {
    return service.create(request);
  }

  @GetMapping("/{accountNumber}")
  public AccountResponse findByAccountNumber(@PathVariable String accountNumber) {
    return service.findByAccountNumber(accountNumber);
  }

  @PostMapping("/{accountNumber}/payment")
  public boolean pay(@PathVariable String accountNumber, Double amount) {
    return service.pay(accountNumber, amount);
  }

  @PostMapping("/{accountNumber}/deposit")
  public boolean deposit(@PathVariable String accountNumber, Double amount) {
    return service.deposit(accountNumber, amount);
  }

}
