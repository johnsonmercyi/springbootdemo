package com.soft.springbootdemo.service.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Override
  public User saveUser(User user) {
    user.setId(generateId());
    return userRepo.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userRepo.findByUsername(username);
  }

  @Override
  public Collection<User> findAll() {
    return userRepo.findAll();
  }

  @Override
  public User updateUser(UUID uuid, User user) {
    Optional<User> oldUser = userRepo.findById(uuid);
    if (oldUser.isPresent()) {
      User newUser = oldUser.get();
      newUser.setUsername(user.getUsername());
      newUser.setPassword(user.getPassword());
      newUser.setEmail(user.getEmail());
      userRepo.save(newUser);
      return newUser;
    }
    return null;
  }

  @Override
  public Optional<User> findById(UUID id) {
    return userRepo.findById(id);
  }
  
}
