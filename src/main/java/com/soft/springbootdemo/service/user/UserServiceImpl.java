package com.soft.springbootdemo.service.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.dto.RoleDTO;
import com.soft.springbootdemo.dto.UserDTO;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.model.UserRole;
import com.soft.springbootdemo.repo.UserRepo;
import com.soft.springbootdemo.repo.UserRoleRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

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
  public UserDTO findUserWithRoles(UUID id) {
    // Fetch user first
    Optional<User> userOptional = userRepo.findById(id);

    if (userOptional.isPresent()) {
      User user = userOptional.get();// get the actual user

      // Map user data to a UserDTO object
      return mapUserToDTO(user, true);
    }
    return null;
  }

  @Override
  public Collection<UserDTO> findAll() {
    List<User> users = userRepo.findAll();
    List<UserDTO> userDTOs = new ArrayList<>();

    for (User user : users) {
      userDTOs.add(mapUserToDTO(user, false));
    }

    return userDTOs;
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

  private UserDTO mapUserToDTO(User user, boolean fetchRoles) {
    // Map user to UserDTO object
    UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getStatus(), user.getCreated(), user.getUpdated(), new ArrayList<>());

    // Fetch and add roles to user if allowed
    if (fetchRoles) {
      // Map UserRoles data to a RoleDTO object
      List<RoleDTO> userRoles = user.getUserRoles().stream()
          .map(userRole -> {
            // Map UserRoles to RoleDTO object
            RoleDTO roleDTO = new RoleDTO(userRole.getId(), userRole.getRole().getName(), userRole.getCreated(), userRole.getUpdated());

            return roleDTO;
          })
          .toList();

      // Set the user role in the UserDTO
      userDTO.setUserRoles(userRoles);
    }

    return userDTO;
  }
  
}
