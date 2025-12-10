package model.market;

import model.abstractClasses.AbstractStock;
import model.entities.Skuperfield;

public class Stock extends AbstractStock {
    private String worthInfo;

    public Stock(String name, String type, double price, String worthInfo){
        super(name, type,  price);
        this.worthInfo = worthInfo;
    }

    public Stock(String name, String type, double price){
        super(name, type, price);
        this.worthInfo = "NaN";
    }

    public String getWorthInfo(){ return worthInfo;}
    @Override
    public boolean isWorthBuying(Skuperfield skuperfield) {
        double chance = Math.random();
        switch (worthInfo) {
            case "перспективная":
                return super.price <= 200. ||
                        skuperfield.getMoney() * 0.4 >= super.price;
            case "неперспективная":
                return skuperfield.getMoney() * 0.05 >= super.price && chance > 0.8;
            default: return chance > 0.6;
        }
    }

}
