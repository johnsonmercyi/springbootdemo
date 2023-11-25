package com.soft.springbootdemo.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/users")
  public Collection<User> getUsers() {
    return userService.findAll();
  }

  @PostMapping("/user")
  public User saveUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @PostMapping("/user/edit/{id}")
  public User updateUser(@RequestBody User user, @PathVariable UUID id) {
    return userService.updateUser(id, user);
  }
}
