package com.soft.springbootdemo.service.product;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.repo.ProductRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepo productRepo;

  @Override
  public Product save(Product product) {
    return productRepo.save(product);
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return productRepo.findById(id);
  }

  @Override
  public Collection<Product> findAll() {
    return productRepo.findAll();
  }

  @Override
  public Product update(UUID uuid, Product product) {
    return null;
  }

  @Override
  public Product findByRefNo(UUID refNo) {
    return productRepo.findByRefNo(refNo);
  }

}
