package com.soft.springbootdemo.service.customer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;

public interface CustomerService {
  public Customer saveCustomer(Customer customer, User user, List<Role> roles);

  public Optional<Customer> findById(UUID id);

  public Collection<Customer> findAll();

  public Customer updateCustomer(UUID uuid, Customer customer);
}
