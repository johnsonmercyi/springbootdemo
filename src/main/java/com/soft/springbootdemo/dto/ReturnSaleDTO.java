package com.soft.springbootdemo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnSaleDTO {
    private UUID id;
    private UUID productId;
    private UUID saleId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime created;
    private LocalDateTime updated;
}
