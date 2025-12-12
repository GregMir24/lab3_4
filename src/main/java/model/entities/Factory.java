package model.entities;

import model.abstractClasses.AbstractEntity;
import model.enums.City;
import model.interfaces.Produceable;

public class Factory extends AbstractEntity implements Produceable {
    private String product;
    private int efficiency;
    private int storage;
    private double productPrice;

    public Factory(String name, City location, String product, int efficiency, double productPrice){
        super(name, location.getName());
        this.product = product;
        this.efficiency = efficiency;
        this.storage = 0;
        this.productPrice = productPrice;
    }

    public void produce(){
        int produced = efficiency;
        storage += produced;

        System.out.printf("%n%s произвела %s %s,", name, produced, product);
        System.out.printf("теперь на складе %s %s.", storage, product);
    }

    public void sell(int quantity, Skuperfield seller) {
        if (quantity > storage){
            System.out.printf("Недостаточно %s на складе, продажа не удалась", product);
        } else {
            storage -= quantity;
            double revenue = quantity * productPrice;
            System.out.printf(" %s принесла доход %s в размере %s%nтенге", name, seller.getName(), revenue);
            seller.setMoney(seller.getMoney() + revenue);
        }
    }

    public int getStorage(){ return storage;}


    public double getProductPrice(){ return productPrice;}

    @Override
    public String toString(){
        return String.format("Factory[name: %s, location: %s, product: %s, efficiency: %s,storage: %s id: %s]",
                name, location, product, efficiency, storage, id);
    }
}
