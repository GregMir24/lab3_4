package scenario;

import model.entities.Barge;
import model.entities.Skuperfield;
import model.entities.Worker;
import model.exceptions.StockNotFoundException;
import model.market.Stock;
import model.records.TradeOrder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ParagraphTwo {
    private Skuperfield skuperfield;
    private Stock stock;

    public ParagraphTwo(Skuperfield skuperfield, Stock stock){
        this.skuperfield = skuperfield;
        this.stock = stock;
    }

    public void ordering(){
        Barge targetBarge = findBarge();

        skuperfield.buyIfWorth(targetBarge, stock, 5);
    }


    private Barge findBarge() {
        ArrayList<Barge> barges = skuperfield.getBarges();
        String typeOfStock = stock.getType();

        if (barges == null || barges.isEmpty()) {
            return null;
        }

        for (Barge targetBarge : barges) {
            ArrayList<Stock> stocks = targetBarge.getStocks();
            if (stocks == null || stocks.isEmpty()) {
                continue;
            }

            for (Stock bargesStock : stocks) {
                if (typeOfStock.equals(bargesStock.getType())) {
                    return targetBarge;
                }
            }
        }
        return null;
    }
}