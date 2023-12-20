package com.soft.springbootdemo.service.sale;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.dto.SaleDTO;
import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.repo.ProductRepo;
import com.soft.springbootdemo.repo.SaleRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class SaleServiceImpl implements SaleService {

  private final SaleRepo saleRepo;
  private final ProductRepo productRepo;

  @Override
  public Sale save(Sale sale) {
    return saleRepo.save(sale);
  }

  @Override
  public Sale save(SaleDTO saleDTO) {
    Optional<Product> product = productRepo.findById(saleDTO.getProductId());
    Sale sale = new Sale();

    if (product.isPresent()) {
      sale.setProduct(product.get());
      sale.setQuantity(saleDTO.getQuantity());
      sale.setTotalPrice(saleDTO.getTotalPrice());
      return saleRepo.save(sale);
    }

    return null;
  }

  @Override
  public Optional<Sale> findById(UUID id) {
    return saleRepo.findById(id);
  }

  @Override
  public Collection<Sale> findAll() {
    return saleRepo.findAll();
  }

  @Override
  public Sale update(UUID uuid, Sale sale) {
    return null;
  }

  
  
}
