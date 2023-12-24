package com.soft.springbootdemo.util;

import java.util.ArrayList;
import java.util.List;

import com.soft.springbootdemo.dto.responsedto.CategoryResponseDTO;
import com.soft.springbootdemo.dto.responsedto.CustomerDTO;
import com.soft.springbootdemo.dto.responsedto.RoleDTO;
import com.soft.springbootdemo.dto.responsedto.SaleItemResponseDTO;
import com.soft.springbootdemo.dto.responsedto.SaleItemResponseDTO.CustomProduct;
import com.soft.springbootdemo.dto.responsedto.SaleItemResponseDTO.CustomSeller;
import com.soft.springbootdemo.dto.responsedto.SaleResponseDTO;
import com.soft.springbootdemo.dto.responsedto.SaleResponseDTO.CustomCustomer;
import com.soft.springbootdemo.dto.responsedto.SellerDTO;
import com.soft.springbootdemo.dto.responsedto.UserDTO;
import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.model.SaleItem;
import com.soft.springbootdemo.model.Seller;
import com.soft.springbootdemo.model.User;

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

  public static SaleItemResponseDTO convertSaleItemToResponseDTO(SaleItem saleItem) {

    SaleResponseDTO sale = new SaleResponseDTO();
    Sale mainSale = saleItem.getSale();
    sale.setId(mainSale.getId());
    sale.setSaleTotal(saleItem.getTotal());
    sale.setCreated(mainSale.getCreated());
    sale.setUpdated(mainSale.getUpdated());

    Customer customer = saleItem.getSale().getCustomer();
    String customerName = String.format(
      "%s %s", 
      customer.getFirstname(), 
      customer.getLastname()
    );

    sale.setCustomer(
      new SaleResponseDTO.CustomCustomer(
        customer.getId(), customerName, customer.getUser().getEmail()));

    
    CustomProduct cstProduct = new SaleItemResponseDTO.CustomProduct();
    Product mainProduct = saleItem.getProduct();
    cstProduct.setId(mainProduct.getId());
    cstProduct.setName(mainProduct.getName());
    cstProduct.setRefNo(mainProduct.getRefNo());
    cstProduct.setPrice(mainProduct.getPrice());
    cstProduct.setCost(mainProduct.getCost());
    cstProduct.setCategory(
      new CategoryResponseDTO(
        mainProduct.getCategory().getId(), 
        mainProduct.getCategory().getName()));

    
    Seller mainSeller = saleItem.getSeller();
    String sellerName = String.format(
      "%s %s", 
      mainSeller.getFirstname(), 
      mainSeller.getLastname()
    );

    CustomSeller cstSeller = new SaleItemResponseDTO.CustomSeller(mainSeller.getId(), sellerName, mainSeller.getUser().getEmail());

    return new SaleItemResponseDTO(
      saleItem.getId(), 
      sale, 
      cstProduct, 
      cstSeller, 
      saleItem.getQuantity(), 
      saleItem.getTotal(), 
      saleItem.getCreated(), 
      saleItem.getUpdated()
    );
  }
}
