package com.soft.springbootdemo.service.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import java.util.List;
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
    return productRepo.save(product);
  }

  @Override
  public Product findByRefNo(UUID refNo) {
    return productRepo.findByRefNo(refNo);
  }

  @Override
  public Collection<Product> findByCategoryName(String catName) {
    List<Product> products = productRepo.findAll();
    List<Product> holdProd = new ArrayList<>();
    // List<Product> prod = products.stream()
    //   .map(product -> {
    //     Product p = new Product();
    //     if(product.getCategory().getName().equals(catName)){
    //       p = product;
    //     }
    //     return p;
    //   })
    //   .toList();  
    // return prod;
    for(Product p : products){
      if(p.getCategory().getName().equalsIgnoreCase(catName)){
        holdProd.add(p);
      }
    }
    return holdProd;
  }

}
