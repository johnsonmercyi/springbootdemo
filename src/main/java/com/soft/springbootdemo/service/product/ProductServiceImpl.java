package com.soft.springbootdemo.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.repo.ProductRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
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
    //I will need map stream explanation
    
    // List<Product> prod = products.stream()
    //   .map(product -> {
    //     if(product.getCategory().getName().equalsIgnoreCase(catName)){
    //       holdProd.add(product);
    //     }
    //     return product;
    //   })
    //   .toList();  
   
    for(Product p : products){
      if(p.getCategory().getName().equalsIgnoreCase(catName)){
        holdProd.add(p);
      }
    }
    return holdProd;
  }

  @Override
  public Collection<Product> findByCategoryId(UUID catId) {
    return productRepo.findAll().stream()
    .filter(product -> product.getCategory().getId().equals(catId))
    .toList();
  } 

}
