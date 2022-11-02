package com.example.tinyftx.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
    
    public int getStockKRWPriceByTicker(String ticker) {

        String URL = "https://finance.naver.com/item/main.nhn?code=" + ticker;
        Document doc; 
        int stockPrice = 0;

        try {
            doc = Jsoup.connect(URL).get(); 
            Elements companyInfoList = doc.select(".new_totalinfo dl>dd");  
            String stockPriceData = companyInfoList.get(3).text().split(" ")[1];
            stockPrice = getPurePrice(stockPriceData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockPrice;
    }

    public int getEthKRWPrice() {
        String URL = "https://www.google.com/finance/quote/ETH-KRW";
        Document doc; 

        int result = 0;

        try {
            doc = Jsoup.connect(URL).get();
            Elements elements = doc.getElementsByClass("YMlKec fxKbKc");
            String price = elements.text().split("\\.")[0];
            result = getPurePrice(price);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public double getStockEthPriceByTicker(String ticker) {

        int krw = getStockKRWPriceByTicker(ticker);
        int eth = getEthKRWPrice();

        return (double)eth/krw;
    }

    public int getPurePrice(String price) {
        String match = "[^0-9]";
        price = price.replaceAll(match, "");
        return Integer.parseInt(price);
    }
}


