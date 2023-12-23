package com.soft.springbootdemo.util;

import java.util.ArrayList;
import java.util.List;

import com.soft.springbootdemo.dto.responsedto.CustomerDTO;
import com.soft.springbootdemo.dto.responsedto.RoleDTO;
import com.soft.springbootdemo.dto.responsedto.SaleResponseDTO;
import com.soft.springbootdemo.dto.responsedto.SaleResponseDTO.CustomCustomer;
import com.soft.springbootdemo.dto.responsedto.SellerDTO;
import com.soft.springbootdemo.dto.responsedto.UserDTO;
import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Role;
import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.model.Seller;
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

  public static SellerDTO mapSellerToDTO(Seller seller, boolean fetchRoles){
    UserDTO userDTO = mapUserToDTO(seller.getUser(), fetchRoles);
    return new SellerDTO(seller.getId(), userDTO, seller.getFirstname(), seller.getLastname(), seller.getGender(), seller.getDob(), seller.getAddress(), seller.getNationality(), seller.getCreated(), seller.getUpdated());
  }

  public static SaleResponseDTO convertSaleToResponseDTO(Sale sale) {
    CustomCustomer cCust = new SaleResponseDTO.CustomCustomer();
    
    Customer customer = sale.getCustomer();
    String customerName = String.format("%s %s", customer.getFirstname(), customer.getLastname());
    cCust.setCustomerId(customer.getId());
    cCust.setCustomerName(customerName);
    cCust.setCustomerEmail(customer.getUser().getEmail());

    return new SaleResponseDTO(sale.getId(), cCust, sale.getSaleTotal(), sale.getCreated(), sale.getUpdated());
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
