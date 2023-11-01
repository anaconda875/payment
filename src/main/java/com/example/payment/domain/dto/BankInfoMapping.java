package com.example.payment.domain.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "app.mapping")
@Data
public class BankInfoMapping {

  private Map<String, Object> banks;

}
