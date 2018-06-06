package com.quick.catchup;

/**
 * Created by Karan Biawat on 10/09/2015.
 */
public interface StockQuoteService {
    double getPrice(String symbol);

    void update(String symbol, double price);
}
