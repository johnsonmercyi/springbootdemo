package com.soft.springbootdemo.service.saleItem;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.SaleItem;
import com.soft.springbootdemo.repo.SaleItemRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

  private final SaleItemRepo saleItemRepo;

  @Override
  public SaleItem save(SaleItem t) {
    return saleItemRepo.save(t);
  }

  @Override
  public Optional<SaleItem> findById(UUID id) {
    return saleItemRepo.findById(id);
  }

  @Override
  public Collection<SaleItem> findAll() {
    return saleItemRepo.findAll();
  }

  @Override
  public SaleItem update(UUID uuid, SaleItem t) {
    return null;
  }
  
}
