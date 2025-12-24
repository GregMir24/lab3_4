package model.entities;

import model.abstractClasses.AbstractEntity;
import model.enums.City;
import model.exceptions.CallException;
import model.exceptions.ConnectionException;
import model.exceptions.StockNotFoundException;
import model.exceptions.WorkerNotFoundException;
import model.market.Stock;
import model.records.TradeOrder;

import java.net.ConnectException;
import java.util.ArrayList;

public class Barge extends AbstractEntity {
    private ArrayList<Stock> stocks = new ArrayList<>();
    private ArrayList<Worker> workers = new ArrayList<>();
    private ArrayList<Barge> connections = new ArrayList<>();

    public Barge(String name, City location){
        super(name, location.getName());
    }

    @Override
    public String toString(){
        return String.format("Barge[city: %s, stocks: %s,%n workers: %s,%n" +
                        "connections: %s,%n name: %s, location: %s, id: %s]",
                location, stocks, workers, connections, name, location, id);
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public ArrayList<Stock> getStocks(){
        return new ArrayList<>(stocks);
    }

    public ArrayList<Worker> getWorkers(){ return new ArrayList<>(workers);}

    public ArrayList<Barge> getConnections(){ return new ArrayList<>(connections);}

    public String getCity(){
        return location;
    }

    public String build(){
        return String.format("Была установлена баржа %s в городе %s ", name, location);
    }

    public void connect(Barge barge) throws IllegalArgumentException, ConnectionException{

        if (barge == null) {
            throw new IllegalArgumentException("Баржа не может быть null");
        }
        if (connections.contains(barge) || this.equals(barge)) {
            throw new ConnectionException(this, barge);
        }

        connections.add(barge);
    }

    public void call(Barge barge, String message) throws IllegalArgumentException, CallException{

        if (barge == null) {
            throw new IllegalArgumentException("Баржа не может быть null");
        }
        if (this.equals(barge) || !connections.contains(barge)) {
            throw new CallException(this, barge);
        }

        System.out.printf("Баржа %s передает по телефону барже %s сообщение: %s",
                this.name, barge.name, message);
    }

    public TradeOrder executeOrder(Worker worker, TradeOrder tradeOrder) throws WorkerNotFoundException, StockNotFoundException{

            if (!workers.contains(worker)){
                throw new WorkerNotFoundException(worker);
            }
            if (!stocks.contains(tradeOrder.stock())){
                throw new StockNotFoundException(tradeOrder.stock(), this);
            }
            return tradeOrder;
    }

}



