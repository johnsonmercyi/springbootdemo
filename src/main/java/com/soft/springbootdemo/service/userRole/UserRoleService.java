package com.soft.springbootdemo.service.userRole;

import java.util.Collection;

import com.soft.springbootdemo.model.UserRole;

public interface UserRoleService {
  public UserRole saveUserRole(UserRole userRole);
  public Collection<UserRole> findAll();
}
