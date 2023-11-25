package com.soft.springbootdemo.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {
  @Id
  private UUID id;
  private String username;
  private String password;
  private String email;

  public User() {
    this(UUID.randomUUID(), null, null, null);
  }
}
