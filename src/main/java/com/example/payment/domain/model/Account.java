package com.example.payment.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_account")
@Data
public class Account {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "account_number", unique = true)
  private String accountNumber;

  private Double balance;

  @Column(name = "user_id")
  private String userId;

  @Enumerated(EnumType.STRING)
  private Vendor vendor;

  public enum Vendor {
    ACB,
    TPB;
  }

}
