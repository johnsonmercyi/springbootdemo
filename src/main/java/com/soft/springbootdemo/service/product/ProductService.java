package com.soft.springbootdemo.service.product;

import java.util.UUID;

import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.service.Service;

public interface ProductService extends Service<Product> {
  public Product findByRefNo(UUID id);
}
