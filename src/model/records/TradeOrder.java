package model.records;

import model.market.Stock;

public record TradeOrder(Stock stock, int quantity, double maxPrice) {

    public double getTotalCost(){
        return quantity * maxPrice;
    }

    public boolean isValid(){
        return quantity > 0 && maxPrice > 0;
    }
}
