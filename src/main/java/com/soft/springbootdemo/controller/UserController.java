package com.soft.springbootdemo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.dto.UserRegistrationDTO;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.service.role.RoleService;
import com.soft.springbootdemo.service.user.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final RoleService roleService;

  @GetMapping
  public Collection<User> findAll() {
    return userService.findAll();
  }

  @PostMapping
  public User saveUser(@RequestBody UserRegistrationDTO userRegDTO) {
    User user = new User();
    user.setUsername(userRegDTO.getUsername());
    user.setPassword(userRegDTO.getPassword());
    user.setEmail(userRegDTO.getEmail());

    List<Role> roles = List.of(roleService.findByName("customer"));

    return userService.saveUserWithRoles(user, roles); //persist both user and customer
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> fetchById(@PathVariable UUID id) {
    return ResponseEntity.ok(userService.findById(id).get());
  }
  

  @PostMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserRegistrationDTO userRegDTO) {
    User user = new User();

    user.setEmail(userRegDTO.getEmail());
    return ResponseEntity.ok(userService.saveUser(user));
  }

  @DeleteMapping("/{id}")
  public User deleteUser(@PathVariable UUID id) {
    // return userService.deleteUser(id);
    return null;
  }
}
