package model.exceptions;

import model.entities.Barge;

import java.awt.*;

public class ConnectionException extends  Exception{
    protected final Barge fromBarge;
    protected final Barge toBarge;

    public ConnectionException(Barge fromBarge, Barge toBarge){
        super("Error");
        this.fromBarge = fromBarge;
        this.toBarge = toBarge;
    }

    @Override
    public String getMessage() {
        if (fromBarge.getId().equals(toBarge.getId())) {
            return "Нельзя соединить баржу саму с собой";
        } else {
            return String.format("Баржа %s не соединена с баржей %s", fromBarge.getName(), toBarge.getName());
        }
    }
}
