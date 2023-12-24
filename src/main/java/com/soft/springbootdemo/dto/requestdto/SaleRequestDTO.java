package com.soft.springbootdemo.dto.requestdto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
// @NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {
  private UUID id;
  private UUID customerId;
  private double saleTotal;
  private LocalDateTime created;
  private LocalDateTime updated;

  public SaleRequestDTO() {
    this(UUID.randomUUID(), null, 0d, null, null);
  }
}
