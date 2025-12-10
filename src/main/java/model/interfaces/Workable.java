package model.interfaces;

import model.entities.Skuperfield;
import model.market.Stock;
import model.records.TradeOrder;

public interface Workable {
    public void work(TradeOrder tradeOrder, Skuperfield skuperfield);
}
