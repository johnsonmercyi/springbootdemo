package com.soft.springbootdemo.dto.responsedto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDTO {
  private UUID id;
  private CustomCustomer customer;
  private double saleTotal;
  private LocalDateTime created;
  private LocalDateTime updated;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CustomCustomer {
    private UUID customerId;
    private String customerName;
    private String customerEmail;
  }
}


