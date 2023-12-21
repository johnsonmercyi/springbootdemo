package com.soft.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.dto.ReturnSaleDTO;
import com.soft.springbootdemo.model.ReturnSale;
import com.soft.springbootdemo.service.returnsale.ReturnSaleService;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/returnsales")
@RequiredArgsConstructor
public class ReturnSaleController {
    
    private final ReturnSaleService returnSaleService;

    @GetMapping
    public ResponseEntity<Collection<ReturnSale>> findAllReturnSales() {
        return ResponseEntity.ok(returnSaleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ReturnSale>> findReturnSaleById(@PathVariable UUID id){
        return ResponseEntity.ofNullable(returnSaleService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<ReturnSale> saveReturnSale(@RequestBody ReturnSaleDTO returnSaleDTO){
        return ResponseEntity.ok(returnSaleService.save(returnSaleDTO));
    }
}
