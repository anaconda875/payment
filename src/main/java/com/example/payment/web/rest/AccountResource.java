package com.example.payment.web.rest;

import com.example.payment.dto.request.AccountRequest;
import com.example.payment.dto.response.AccountResponse;
import com.example.payment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountResource {

  private final AccountService service;

  @PostMapping
  public AccountResponse create(@RequestBody AccountRequest request) {
    return service.create(request);
  }

}
