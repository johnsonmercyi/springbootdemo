package com.soft.springbootdemo.service.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.soft.springbootdemo.model.User;

public interface UserService {
  public User saveUser(User user);
  public Optional<User> findById(UUID id);
  public User findByUsername(String username);
  public Collection<User> findAll();
  public User updateUser(UUID uuid, User user);
}
