package model.entities;

import model.abstractClasses.AbstractEntity;
import model.interfaces.Workable;
import model.records.TradeOrder;

public class Worker extends AbstractEntity implements Workable {
    private String type;

    public Worker(String name, Barge barge, String type){
        super(name, barge.getName());
        this.type = type;
    }

    public void work(TradeOrder tradeOrder, Skuperfield skuperfield){
        String stockName = tradeOrder.stock().getName();
        String type = tradeOrder.stock().getType();
        String id = tradeOrder.stock().getId();
        String customer = skuperfield.getName();
        int quantity = tradeOrder.quantity();
        double totalPrice = tradeOrder.getTotalCost();
        System.out.printf("%s выполняет приказ %s:", name, customer);
        System.out.printf("покупает %s акцию %s под уникальным номером %s в количестве %s штук%nпо цене %s тенге.",
                type, stockName, id, quantity, totalPrice);
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString(){
        return String.format("Worker[type: %s, name: %s, location: %s, id: %s]",
                type, name, location, super.id);
    }

}
