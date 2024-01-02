package com.soft.springbootdemo.service.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.dto.responsedto.UserDTO;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.model.UserRole;
import com.soft.springbootdemo.repo.RoleRepo;
import com.soft.springbootdemo.repo.UserRepo;
import com.soft.springbootdemo.repo.UserRoleRepo;
import com.soft.springbootdemo.util.Util;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;
  private final UserRoleRepo userRoleRepo;
  private final RoleRepo roleRepo;

  @Override
  public User saveUser(User user) {
    return userRepo.save(user);
  }

  @Override
  public User saveUserWithRoles(User user, List<String> roles) {
    User savedUser = userRepo.save(user);// save user first
    
    // Saves the user roles in the user_roles table
    // depending on how many roles were assigned to this user
    for (String role : roles) {
      UserRole userRole = new UserRole();
      userRole.setRole(roleRepo.findByName(role));
      userRole.setUser(savedUser);

      userRoleRepo.save(userRole); // user user_roles
    }

    return savedUser;//return saved user
  }

  @Override
  public UserDTO findByUsername(String username) {
    User user = userRepo.findByUsername(username);
    if (user != null) {
      // Map user data to a UserDTO object
      return Util.mapUserToDTO(user, true);
    }
    return null;
  }

  @Override
  public Collection<UserDTO> findAll() {
    List<User> users = userRepo.findAll();
    List<UserDTO> userDTOs = new ArrayList<>();

    for (User user : users) {
      userDTOs.add(Util.mapUserToDTO(user, false));
    }

    return userDTOs;
  }

  @Override
  public UserDTO updateUser(UUID uuid, User user) {
    Optional<User> oldUser = userRepo.findById(uuid);
    if (oldUser.isPresent()) {
      User newUser = oldUser.get();
      newUser.setUsername(user.getUsername());
      newUser.setPassword(user.getPassword());
      newUser.setEmail(user.getEmail());
      userRepo.save(newUser);
      return Util.mapUserToDTO(newUser, true);
    }
    return null;
  }

  @Override
  public Optional<UserDTO> findById(UUID id) {
    // Fetch user first
    Optional<User> userOptional = userRepo.findById(id);

    if (userOptional.isPresent()) {
      // Map user data to a UserDTO object
      return Optional.ofNullable(Util.mapUserToDTO(userOptional.get(), true));
    }
    return Optional.empty();
  }
  
}
