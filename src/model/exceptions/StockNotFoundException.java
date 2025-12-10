package model.exceptions;

import model.entities.Barge;
import model.market.Stock;

public class StockNotFoundException extends Exception{
    private final Stock stock;
    private final Barge barge;

    public StockNotFoundException(Stock stock, Barge barge){
        super("Error");
        this.stock = stock;
        this.barge = barge;
    }


    @Override
    public String getMessage(){
        return String.format("На барже %s отсутствует %s акция", barge.getName(), stock.getId());
    }
}
