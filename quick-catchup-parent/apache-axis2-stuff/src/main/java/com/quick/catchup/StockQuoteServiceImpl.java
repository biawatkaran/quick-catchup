package com.quick.catchup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Karan Biawat on 10/09/2015.
 */
public class StockQuoteServiceImpl implements StockQuoteService {

    private Map<String, Double> map = new HashMap<>();

    @Override
    public double getPrice(String symbol){
        Double price = map.get(symbol);

        if(price != null) {
            return price;
        }

        return 0.00;
    }

    @Override
    public void update(String symbol, double price){
        map.put(symbol, price);
    }
}
