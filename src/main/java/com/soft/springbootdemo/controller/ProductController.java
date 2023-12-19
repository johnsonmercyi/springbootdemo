package com.soft.springbootdemo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.springbootdemo.dto.ProductDTO;
import com.soft.springbootdemo.model.Category;
import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.service.category.CategoryService;
import com.soft.springbootdemo.service.product.ProductService;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
// @Log4j2
public class ProductController {
  private final ProductService productService;
  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Collection<Product>> findAllProducts() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable UUID id) {
    return ResponseEntity.ok(productService.findById(id).get());
  }

  @GetMapping("/catname/{catName}")
  public ResponseEntity<Collection<Product>> findProductByCatName(@PathVariable String catName) {
    return ResponseEntity.ok(productService.findByCategoryName(catName));
  }

  @PostMapping
  public ResponseEntity<Object> saveProduct(@RequestBody ProductDTO productDTO) {
    Optional<Category> optCategory = categoryService.findById(productDTO.getCategoryId());
    if (optCategory.isPresent()) {
      Category category = optCategory.get();

      Product product = new Product();
      product.setCategory(category);
      product.setName(productDTO.getName());
      product.setPrice(productDTO.getPrice());
      product.setCost(productDTO.getCost());
      product.setDescription(productDTO.getDescription());

      return ResponseEntity.ok(productService.save(product));
    }

    Map<String, Object> error = new HashMap<>();
    error.put("error code", HttpStatus.FAILED_DEPENDENCY);
    error.put("error", true);
    error.put("message", "Couldn't find category");

    return ResponseEntity.badRequest().body(error);

    // return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
  }

  @PostMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO){
    Optional<Product> product = productService.findById(id);

    if (product.isPresent()){
      Product pdt = product.get();
      pdt.setName(productDTO.getName());
      pdt.setDescription(productDTO.getDescription());
      pdt.setCost(productDTO.getCost());
      pdt.setPrice(productDTO.getPrice());
      pdt.setRefNo(productDTO.getRefNo());

      return ResponseEntity.ok(productService.update(id, pdt));

    }
    return null;
  }

}
