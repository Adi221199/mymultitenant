package com.example.mymultitenant.service.impl;

import com.example.mymultitenant.model.Stock;
import com.example.mymultitenant.repository.StockRepository;
import com.example.mymultitenant.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    public void createStock(Stock stock) {
        stockRepository.save(stock);
    }
}
