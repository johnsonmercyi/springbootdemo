package com.soft.springbootdemo.util;

import java.util.ArrayList;
import java.util.List;

import com.soft.springbootdemo.dto.responsedto.CustomerDTO;
import com.soft.springbootdemo.dto.responsedto.RoleDTO;
import com.soft.springbootdemo.dto.responsedto.UserDTO;
import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.User;
import com.soft.springbootdemo.model.UserRole;

public class Util {
  public static UserDTO mapUserToDTO(User user, boolean fetchRoles) {
    // Map user to UserDTO object
    UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getStatus(), user.getCreated(), user.getUpdated(), new ArrayList<>());

    // Fetch and add roles to user if allowed
    if (fetchRoles) {
      // Map UserRoles data to a RoleDTO object
      List<RoleDTO> userRoles = user.getUserRoles() // fetch UserRoles
          .stream() // Convert to stream
          .map(userRole -> {
            // Map UserRoles to RoleDTO object
            RoleDTO roleDTO = new RoleDTO(userRole.getId(), userRole.getRole().getName(), userRole.getCreated(), userRole.getUpdated());

            return roleDTO;
          }).toList();

      // Set the user role in the UserDTO
      userDTO.setUserRoles(userRoles);
    }

    return userDTO;
  }

  public static CustomerDTO mapCustomerToDTO(Customer customer, boolean fetchRoles) {
    UserDTO userDTO = mapUserToDTO(customer.getUser(), fetchRoles);
    return new CustomerDTO(customer.getId(), userDTO, customer.getFirstname(), customer.getLastname(), customer.getGender(), customer.getDob(), customer.getAddress(), customer.getNationality(), customer.getCreated(), customer.getUpdated());
  }

  // ⚠️ Please this is not to be used yet!
  public static User mapUserDTOToUser(UserDTO userDTO) {
    User user = new User();

    user.setId(userDTO.getId());
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    user.setEmail(userDTO.getEmail());
    user.setStatus(userDTO.getStatus());
    user.setCreated(userDTO.getCreated());
    user.setUpdated(userDTO.getUpdated());


    List<UserRole> userRoles = userDTO.getUserRoles() // fetch UserRoles
        .stream() // Convert to stream
        .map(role -> {
          // Map UserRoles to RoleDTO object
          UserRole userRole = new UserRole();
          userRole.setUser(user);
          userRole.setRole(new Role(role.getId(), role.getName(), role.getCreated(), role.getCreated(), new ArrayList<UserRole>()));

          return userRole;
        }).toList();

    // user.setUserRoles(userRoles);

    return user;
  }
}
