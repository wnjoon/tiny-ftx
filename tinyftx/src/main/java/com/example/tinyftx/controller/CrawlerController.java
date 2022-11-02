package com.example.tinyftx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tinyftx.service.CrawlerService;

@RestController
public class CrawlerController {


    private CrawlerService service;

    public CrawlerController(CrawlerService service) {
        this.service = service;
    }
    
    @GetMapping("/company/{ticker}")
    @ResponseBody
    public double stockPrice(
        @PathVariable String ticker
    ) {
        return service.getStockEthPriceByTicker(ticker);
    }

    @GetMapping("/eth")
    @ResponseBody
    public int ethPrice() {
        return service.getEthKRWPrice();
    }
}
