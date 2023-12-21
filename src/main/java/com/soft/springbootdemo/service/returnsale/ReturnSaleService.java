package com.soft.springbootdemo.service.returnsale;

import com.soft.springbootdemo.dto.ReturnSaleDTO;
import com.soft.springbootdemo.model.ReturnSale;
import com.soft.springbootdemo.service.Service;

public interface ReturnSaleService extends Service<ReturnSale> {
    public ReturnSale save(ReturnSaleDTO returnSaleDTO);
}
