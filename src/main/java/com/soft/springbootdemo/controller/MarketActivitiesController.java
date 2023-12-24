package com.soft.springbootdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.dto.requestdto.SaleItemRequestDTO;
import com.soft.springbootdemo.dto.responsedto.SaleResponseDTO;



@RestController
@RequestMapping("/api/ma")
public class MarketActivitiesController {
  @PostMapping
  public ResponseEntity<SaleResponseDTO> makeSale(@RequestBody SaleItemRequestDTO saleItemRequestDTO) {
      //TODO: process POST request
      
      return null;
  }
  
  
}
