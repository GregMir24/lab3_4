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
        System.out.println("=== Базовый тест ===");
        System.out.println("=== ИНИЦИАЛИЗАЦИЯ ОБЪЕКТОВ ===");
        System.out.println();

        Skuperfield skuperfield = new Skuperfield("Илон Маск", City.SAN_KOMARIK, 100000000000.0);
        System.out.println("Создан Skuperfield: " + skuperfield.getName());
        System.out.println("Локация: " + skuperfield.getLocation());
        System.out.println("Деньги: " + skuperfield.getMoney());
        System.out.println();

        Barge waterFlotBarge = new Barge("Вотерфлот", City.DAVILON);
        Barge utairBarge = new Barge("Ютэйр", City.GRABENBERG);
        Barge hacapuriBarge = new Barge("Хачапурка", City.SAN_KOMARIK);

        System.out.println("Созданы баржи:");
        System.out.println("- " + waterFlotBarge.getName() + " в " + waterFlotBarge.getCity());
        System.out.println("- " + utairBarge.getName() + " в " + utairBarge.getCity());
        System.out.println("- " + hacapuriBarge.getName() + " в " + hacapuriBarge.getCity());
        System.out.println();

        Factory factory = new Factory("Пельмешкопродакшн", City.GRABENBERG, "пельмени", 2000, 150);
        System.out.println("Создана фабрика: " + factory);
        System.out.println();

        Stock stock = new Stock("Т-банк", "гигантская", 200);
        System.out.println("Создана акция: " + stock);
        System.out.println();

        Worker screamer1 = new Worker("Александр", waterFlotBarge, "крикун");
        Worker screamer2 = new Worker("Андрюха", utairBarge, "крикун");
        Worker screamer3 = new Worker("Хуй зинь пинь", hacapuriBarge, "крикун");

        System.out.println("Созданы работники:");
        System.out.println("- " + screamer1.getName() + " на барже " + waterFlotBarge.getName());
        System.out.println("- " + screamer2.getName() + " на барже " + utairBarge.getName());
        System.out.println("- " + screamer3.getName() + " на барже " + hacapuriBarge.getName());
        System.out.println();

        waterFlotBarge.addStock(stock);
        utairBarge.addStock(stock);
        hacapuriBarge.addStock(stock);
        System.out.println("Акция добавлена на все баржи");
        System.out.println();

        skuperfield.addFactory(factory);
        skuperfield.addBarge(waterFlotBarge);
        skuperfield.addBarge(utairBarge);
        System.out.println("Фабрика и баржи добавлены Скуперфильду");
        System.out.println();

        System.out.println("=== НАЧАЛО СЦЕНАРИЯ ===");

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