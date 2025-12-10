package model.interfaces;

import model.entities.Skuperfield;

public interface Produceable {
    void produce();;
    void sell(int quantity, Skuperfield seller);
    int getStorage();
    double getProductPrice();
}
