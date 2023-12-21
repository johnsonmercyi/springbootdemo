package com.soft.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.model.ReturnSale;
import com.soft.springbootdemo.service.returnsale.ReturnSaleService;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/returnsales")
@RequiredArgsConstructor
public class ReturnSaleController {
    
    private final ReturnSaleService returnSaleService;

    @GetMapping
    public ResponseEntity<Collection<ReturnSale>> findAllReturnSales() {
        return ResponseEntity.ok(returnSaleService.findAll());
    }
    
}
