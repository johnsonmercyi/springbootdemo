package com.soft.springbootdemo.service.sale;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.dto.requestdto.SaleRequestDTO;
import com.soft.springbootdemo.dto.responsedto.SaleResponseDTO;
import com.soft.springbootdemo.model.Customer;
import com.soft.springbootdemo.model.Sale;
import com.soft.springbootdemo.repo.CustomerRepo;
import com.soft.springbootdemo.repo.SaleRepo;
import com.soft.springbootdemo.util.Util;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class SaleServiceImpl implements SaleService {

  private final SaleRepo saleRepo;
  private final CustomerRepo customerRepo;

  @Override
  public SaleResponseDTO save(SaleRequestDTO dto) {
    Optional<Customer> customer = customerRepo.findById(dto.getCustomerId());
    Sale sale = new Sale();

    if (customer.isPresent()) {
      sale.setCustomer(customer.get());
      sale.setSaleTotal(dto.getSaleTotal());
      Sale savedSale = saleRepo.save(sale);
      return Util.convertSaleToResponseDTO(savedSale);
    }

    return null;
  }

  @Override
  public Optional<SaleResponseDTO> findById(UUID id) {
    return Optional.ofNullable(Util.convertSaleToResponseDTO(saleRepo.findById(id).get()));
  }

  @Override
  public Collection<SaleResponseDTO> findAll() {
    Collection<Sale> sales = saleRepo.findAll();
    Collection<SaleResponseDTO> salesResponseList = new ArrayList<>();
    for (Sale sale : sales) {
      salesResponseList.add(Util.convertSaleToResponseDTO(sale));
    }
    return salesResponseList;
  }

  @Override
  public SaleResponseDTO update(UUID uuid, SaleRequestDTO t) {
    return null;
  }
  
}
