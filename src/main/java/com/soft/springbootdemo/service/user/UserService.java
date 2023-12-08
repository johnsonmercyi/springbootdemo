package com.soft.springbootdemo.service.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.service.Service;

public interface UserService extends Service {
  public User saveUser(User user);
  public User saveUserWithRoles(User user, List<Role> roles);
  public Optional<User> findById(UUID id);
  public User findByUsername(String username);
  public User findByIdWithRoles(UUID id);
  public Collection<User> findAll();
  public User updateUser(UUID uuid, User user);
}
