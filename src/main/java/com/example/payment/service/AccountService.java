package com.example.payment.service;

import com.example.payment.dto.request.AccountRequest;
import com.example.payment.dto.response.AccountResponse;

public interface AccountService {

  AccountResponse create(AccountRequest request);

}
