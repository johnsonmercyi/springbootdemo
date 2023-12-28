package com.soft.springbootdemo.service.ProductInventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.stereotype.Service;

import com.soft.springbootdemo.model.ProductInventory;
import com.soft.springbootdemo.repo.ProductInventoryRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final ProductInventoryRepo productInventoryRepo;

    @Override
    public ProductInventory save(ProductInventory pi) {
       return productInventoryRepo.save(pi);
    }

    @Override
    public Collection<ProductInventory> findAllInventory() {
       List <ProductInventory> inventories = productInventoryRepo.findAll();
       List <ProductInventory> holdProductInventories = new ArrayList<>();
       for(ProductInventory pi: inventories){
        holdProductInventories.add(pi);
       }
       return holdProductInventories;
    }

    @Override
    public Optional<ProductInventory> findByProductId(UUID id) {
        return productInventoryRepo.findByProductId(id);
    }

    @Override
    public Optional<ProductInventory> findByInventoryId(UUID id) {
        return productInventoryRepo.findById(id);
    }

    @Override
    public ProductInventory update(UUID id, ProductInventory pi) {
        Optional <ProductInventory> optProductInventory = productInventoryRepo.findById(id);
        if(optProductInventory.isPresent()){
            ProductInventory oldProductInventory = optProductInventory.get();
            oldProductInventory.setProduct(pi.getProduct());
            oldProductInventory.setQuantity(pi.getQuantity());

            return productInventoryRepo.save(oldProductInventory);
        }
        return null;
    } 
    
}
