package com.example.payment.config;

import com.example.payment.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ConsumerBindingConfig {

  @Bean
  public Consumer<Message<Map<String, Object>>> pay(AccountService accountService) {
    return msg -> {
      System.out.println(msg);
      Map<String, Object> payload = msg.getPayload();
      accountService.pay(payload.get("accountNumber").toString(),
        Double.valueOf(payload.get("amount").toString()));
    };
  }

}
