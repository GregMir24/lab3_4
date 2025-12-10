package model.enums;

import javax.swing.*;

public enum City {
    DAVILON("Давилон"),
    GRABENBERG("Грабенберг"),
    SAN_KOMARIK("Сан-Комарик");

    private final String name;

    City(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
