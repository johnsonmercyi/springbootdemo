package com.soft.springbootdemo.dto.requestdto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {
  private UUID id;
  private UUID customerId;
  private double saleTotal;
  private LocalDateTime created;
  private LocalDateTime updated;
}
