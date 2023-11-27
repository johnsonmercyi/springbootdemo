package com.soft.springbootdemo.service.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.service.Service;

public interface UserService extends Service {
  public User saveUser(User user);
  public Optional<User> findById(UUID id);
  public User findByUsername(String username);
  public Collection<User> findAll();
  public User updateUser(UUID uuid, User user);
}
