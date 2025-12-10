package model.exceptions;

import model.entities.Barge;

public class CallException extends ConnectionException{
    public CallException(Barge fromBarge, Barge toBarge){
        super(fromBarge, toBarge);
    }

    @Override
    public String getMessage(){
        return String.format("Не удалось осуществить звонок между %s и %s," +
                " так как они не соединены", fromBarge, toBarge);
    }
}
