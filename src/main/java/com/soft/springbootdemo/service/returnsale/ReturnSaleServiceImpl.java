package com.soft.springbootdemo.service.returnsale;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.dto.ReturnSaleDTO;
import com.soft.springbootdemo.model.Product;
import com.soft.springbootdemo.model.ReturnSale;
import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.repo.ProductRepo;
import com.soft.springbootdemo.repo.ReturnSaleRepo;
import com.soft.springbootdemo.repo.SaleRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnSaleServiceImpl implements ReturnSaleService {

  private final ReturnSaleRepo returnSaleRepo;
  private final ProductRepo productRepo;
  private final SaleRepo saleRepo;

  @Override
  public ReturnSale save(ReturnSale returnSale) {
    return returnSaleRepo.save(returnSale);
  }

  @Override
  public Optional<ReturnSale> findById(UUID id) {
    return returnSaleRepo.findById(id);
  }

  @Override
  public Collection<ReturnSale> findAll() {
    return returnSaleRepo.findAll();
  }

  @Override
  public ReturnSale update(UUID uuid, ReturnSale returnSale) {
    return null;
  }

  @Override
  public ReturnSale save(ReturnSaleDTO returnSaleDTO) {
    // Optional<Product> product =
    // productRepo.findById(returnSaleDTO.getProductId());
    Optional<Sale> sale = saleRepo.findById(returnSaleDTO.getSaleId());

    if (sale.isPresent()) {
      ReturnSale returnSale = new ReturnSale();
      // returnSale.setProduct(product.get());
      returnSale.setSale(sale.get());
      // returnSale.setQuantity(returnSaleDTO.getQuantity());
      returnSale.setTotalPrice(returnSaleDTO.getTotalPrice());
      // save
      return returnSaleRepo.save(returnSale);
    }
    return null;
  }

}
