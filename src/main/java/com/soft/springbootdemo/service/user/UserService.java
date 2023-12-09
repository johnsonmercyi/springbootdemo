package com.soft.springbootdemo.service.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.soft.springbootdemo.dto.responsedto.UserDTO;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.service.Service;

public interface UserService extends Service {
  public User saveUser(User user);
  public User saveUserWithRoles(User user, List<Role> roles);
  public Optional<UserDTO> findById(UUID id);
  public UserDTO findByUsername(String username);
  public Collection<UserDTO> findAll();
  public User updateUser(UUID uuid, User user);
}
