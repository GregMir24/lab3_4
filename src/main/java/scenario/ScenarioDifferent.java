package scenario;

import model.entities.Barge;
import model.entities.Factory;
import model.entities.Skuperfield;
import model.entities.Worker;
import model.enums.City;
import model.exceptions.ConnectionException;
import model.market.Stock;

public class ScenarioDifferent {

    public void execute() {
        System.out.println("=== СЦЕНАРИЙ 2: РАЗНЫЕ ПАРАМЕТРЫ ===");
        System.out.println();

        Skuperfield skuperfield = new Skuperfield("Богатый", City.DAVILON, 1000000.0);
        System.out.println("Скупёрфильд: " + skuperfield.getName());
        System.out.println("Деньги: " + skuperfield.getMoney());
        System.out.println();

        Barge barge1 = new Barge("Торговая", City.GRABENBERG);
        Barge barge2 = new Barge("Транспортная", City.SAN_KOMARIK);
        System.out.println("Баржи: " + barge1.getName() + ", " + barge2.getName());
        System.out.println();

        Factory factory1 = new Factory("Кондитерская", City.SAN_KOMARIK, "конфеты", 1000, 300);
        Factory factory2 = new Factory("Мебельная", City.DAVILON, "стулья", 200, 1500);
        System.out.println("Фабрики созданы");
        System.out.println();

        Stock stock1 = new Stock("Металл", "крупная", 500);
        Stock stock2 = new Stock("IT", "рисковая", 100, "перспективная");
        System.out.println("Акции созданы");
        System.out.println();

        Worker worker1 = new Worker("Бухгалтер", barge1, "бухгалтер");
        Worker worker2 = new Worker("Менеджер", barge1, "менеджер");
        Worker worker3 = new Worker("Курьер", barge2, "курьер");
        System.out.println("- " + worker1.getName() + " добавлен"  );
        System.out.println("- " + worker2.getName() + " добавлен " );
        System.out.println("- " + worker3.getName() + " добавлен " );
        System.out.println();

        barge1.addStock(stock1);
        barge1.addStock(stock2);
        barge2.addStock(stock1);
        System.out.println("Акции распределены по баржам");
        System.out.println();

        try {
            barge1.connect(barge2);
            System.out.println("Баржи соединены");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректные аргументы для соединения: " + e.getMessage());
        } catch (ConnectionException e) {
            System.out.println("Не удалось соединить баржи: " + e.getMessage());
        }

        skuperfield.addFactory(factory1);
        skuperfield.addFactory(factory2);
        skuperfield.addBarge(barge1);
        skuperfield.addBarge(barge2);
        System.out.println("Все объекты добавлены");
        System.out.println();

        System.out.println("=== НАЧАЛО ТЕСТА ===");

        try {
            ParagraphOne p1 = new ParagraphOne();
            p1.buildingBarges(skuperfield.getBarges());
            p1.connectingBarges(skuperfield.getBarges().size());

            int bargeIndex = Math.min(0, skuperfield.getBarges().size() - 1);
            int workerIndex = 0;
            p1.informationAboutBarges(skuperfield.getBarges(), bargeIndex, workerIndex);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка индекса в ParagraphOne: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректные данные в ParagraphOne: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Общая ошибка в ParagraphOne: " + e.getClass().getSimpleName());
        }

        try {
            ParagraphTwo p2_1 = new ParagraphTwo(skuperfield, stock1);
            p2_1.ordering();
            System.out.println();

            ParagraphTwo p2_2 = new ParagraphTwo(skuperfield, stock2);
            p2_2.ordering();

        } catch (IllegalStateException e) {
            System.out.println("Ошибка состояния в ParagraphTwo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка выполнения ParagraphTwo: " + e.getMessage());
        }

        try {
            ParagraphThree p3 = new ParagraphThree();
            p3.producing(skuperfield);
        } catch (Exception e) {
            System.out.println("Ошибка в ParagraphThree: " + e.getMessage());
        }

        System.out.println("\n=== СЦЕНАРИЙ УСПЕШНО ВЫПОЛНЕН ===");
    }
}