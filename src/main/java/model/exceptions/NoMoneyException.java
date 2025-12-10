package model.exceptions;

public class NoMoneyException extends Exception{
    private final String buyerName;

    public NoMoneyException(String buyerName){
        super("Error");
        this.buyerName = buyerName;
    }

    @Override
    public String getMessage(){
        return String.format("У %s недостаточно средств", buyerName);
    }
}
