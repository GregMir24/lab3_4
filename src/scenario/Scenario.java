package scenario;

import model.entities.Barge;
import model.entities.Factory;
import model.entities.Skuperfield;
import model.entities.Worker;
import model.enums.City;
import model.exceptions.ConnectionException;
import model.market.Stock;

public class Scenario {

    public void execute(){
        Skuperfield skuperfield = new Skuperfield("Илон Маск", City.SAN_KOMARIK, 100.0);
        Barge waterFlotBarge = new Barge("Вотерфлот", City.DAVILON);
        Barge utairBarge = new Barge("Ютэйр", City.GRABENBERG);
        Barge hacapuriBarge = new Barge("Хачапурка", City.SAN_KOMARIK);

        Factory factory = new Factory("Пельмешкопродакшн", City.GRABENBERG, "пельмени", 2000, 150);

        Stock stock = new Stock("Т-банк", "гигантская", 2, "перспективная");

        Worker screamer1 = new Worker("Александр", waterFlotBarge, "крикун");
        Worker screamer2 = new Worker("Андрюха", utairBarge, "крикун");
        Worker screamer3 = new Worker("Хуй зинь пинь", hacapuriBarge, "крикун");

        waterFlotBarge.addWorker(screamer1);
        utairBarge.addWorker(screamer2);
        hacapuriBarge.addWorker(screamer3);

        waterFlotBarge.addStock(stock);
        utairBarge.addStock(stock);
        hacapuriBarge.addStock(stock);

        try{
            waterFlotBarge.connect(utairBarge);
            waterFlotBarge.connect(hacapuriBarge);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка аргумента: " + e.getMessage());
        } catch (ConnectionException e) {
            System.out.println("Ошибка соединения: " + e.getMessage());
        }

        skuperfield.addFactory(factory);
        skuperfield.addBarge(waterFlotBarge);
        skuperfield.addBarge(utairBarge);
//
//
//
//
        try {
            ParagraphOne p1 = new ParagraphOne();
            p1.buildingBarges(skuperfield.getBarges());
            p1.connectingBarges(skuperfield.getBarges().size());
            p1.informationAboutBarges(skuperfield.getBarges(), 0, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка доступа к индексу: " + e.getMessage());
        }

        try {
            ParagraphTwo p2 = new ParagraphTwo(skuperfield, stock);
            p2.ordering();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка доступа к индексу: " + e.getMessage());
        }

        ParagraphThree p3 = new ParagraphThree();
        p3.producing(skuperfield);

    }
}