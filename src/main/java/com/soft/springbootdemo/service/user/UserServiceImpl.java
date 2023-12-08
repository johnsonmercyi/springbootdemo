package com.soft.springbootdemo.service.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.model.UserRole;
import com.soft.springbootdemo.repo.UserRepo;
import com.soft.springbootdemo.repo.UserRoleRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;
  private final UserRoleRepo userRoleRepo;

  @Override
  public User saveUser(User user) {
    // user.setId(generateId());
    return userRepo.save(user);
  }

  @Override
  public User saveUserWithRoles(User user, List<Role> roles) {
    User savedUser = userRepo.save(user);// save user first
    
    // Saves the user roles in the user_roles table
    // depending on how many roles were assigned to this user
    for (Role role : roles) {
      UserRole userRole = new UserRole();
      userRole.setRole(role);
      userRole.setUser(savedUser);

      userRoleRepo.save(userRole); // user user_roles
    }

    return savedUser;//return saved user
  }

  @Override
  public User findByUsername(String username) {
    return userRepo.findByUsername(username);
  }

  @Override
  public User findByIdWithRoles(UUID id) {
    return userRepo.findById(id)
    .map((user) -> {
      user.getUserRoles().size();
      return user;
    }).orElse(null);
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
