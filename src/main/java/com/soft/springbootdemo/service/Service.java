package com.soft.springbootdemo.service;

import java.util.UUID;

public interface Service {
  default UUID generateId() {
    return UUID.randomUUID();
  }
}
