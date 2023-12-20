package com.soft.springbootdemo.service.sale;

import com.soft.springbootdemo.dto.SaleDTO;
import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.service.Service;

public interface SaleService extends Service<Sale> {
  public Sale save(SaleDTO saleTO);
}
