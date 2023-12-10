package com.soft.springbootdemo.service.customer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.repo.CustomerRepo;
import com.soft.springbootdemo.service.role.RoleService;
import com.soft.springbootdemo.service.user.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepo customerRepo;
  private final UserService userService;

  @Override
  public Customer saveCustomer(Customer customer, User user, List<Role> roles) {
    // Save user first
    userService.saveUserWithRoles(user, roles);

    // Then save the customer lastly
    return customerRepo.save(customer);
  }

  @Override
  public Optional<Customer> findById(UUID id) {
    return customerRepo.findById(id);
  }

  @Override
  public Collection<Customer> findAll() {
    return customerRepo.findAll();
  }

  @Override
  public Customer updateCustomer(UUID uuid, Customer customer) {
    Optional<Customer> optionalCust = customerRepo.findById(uuid);
    if (optionalCust.isPresent()) {
      Customer oldCustomer = optionalCust.get();
      oldCustomer.setFirstname(customer.getFirstname());
      oldCustomer.setLastname(customer.getLastname());
      oldCustomer.setGender(customer.getGender());
      oldCustomer.setAddress(customer.getAddress());
      oldCustomer.setNationality(customer.getNationality());
      oldCustomer.setDob(customer.getDob());
      
      return customerRepo.save(oldCustomer);
    }
    return null;
  }


}
