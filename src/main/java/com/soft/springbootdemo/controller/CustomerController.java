package com.soft.springbootdemo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.dto.UserRegistrationDTO;
import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.service.customer.CustomerService;
import com.soft.springbootdemo.service.role.RoleService;
import com.soft.springbootdemo.service.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;
  private final RoleService roleService;

  @PostMapping
  public ResponseEntity<Customer> saveCustomer(@RequestBody UserRegistrationDTO userRegDTO) {
    // Prepare user
    User user = new User();
    user.setUsername(userRegDTO.getUsername());
    user.setPassword(userRegDTO.getPassword());
    user.setEmail(userRegDTO.getEmail());
    
    // Prepare user roles
    List<Role> roles = List.of(roleService.findByName("customer"));

    // Prepare customer
    Customer customer = new Customer();
    customer.setUser(user);
    customer.setFirstname(userRegDTO.getFirstname());
    customer.setLastname(userRegDTO.getLastname());
    customer.setGender(userRegDTO.getGender());
    customer.setAddress(userRegDTO.getAddress());
    customer.setNationality(userRegDTO.getNationality());
    customer.setDob(userRegDTO.getDob());

    // Save customer
    return ResponseEntity.ok(
      customerService.saveCustomer(customer, user, roles)
    );
    
  }
}
