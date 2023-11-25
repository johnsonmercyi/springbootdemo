package com.soft.springbootdemo.model;

import java.util.UUID;

public class MainOld {
  public static void main(String[] args) {
    User user = new User();
    user.setUsername("soft");
    user.setEmail("soft@email.com");
    user.setPassword("soft123");
    System.out.println(user);
  }
}
