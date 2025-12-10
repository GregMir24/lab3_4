package model.abstractClasses;

import model.entities.Skuperfield;
import model.interfaces.Tradeable;
import utils.IdGen;

import java.util.Objects;

public abstract class AbstractStock implements Tradeable {
    protected String id;
    protected String name;
    protected String type;
    protected double price;

    public AbstractStock(String name, String type, double price){
        this.id = IdGen.generateId(name);
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    public abstract boolean isWorthBuying(Skuperfield skuperfield);

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractEntity that = (AbstractEntity) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return String.format("Stock[id=%s, name=%s, type=%s, price=%.2f]",
                getId(), getName(), getType(), price);
    }
}
