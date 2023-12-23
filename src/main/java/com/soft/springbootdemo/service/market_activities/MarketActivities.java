package com.soft.springbootdemo.service.market_activities;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.repo.ReturnSaleRepo;
import com.soft.springbootdemo.repo.SaleRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class MarketActivities {
  
  private final SaleRepo saleRepo;
  private final ReturnSaleRepo returnSaleRepo;
}
