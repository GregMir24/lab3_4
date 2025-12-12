package scenario;

import model.entities.Barge;
import model.entities.Factory;
import model.entities.Skuperfield;
import model.entities.Worker;
import model.enums.City;
import model.market.Stock;

public class ScenarioSmall {

    public void execute() {
        System.out.println("=== Минимальный тест ===");
        System.out.println("=== ИНИЦИАЛИЗАЦИЯ ОБЪЕКТОВ ===");

        Skuperfield skuperfield = new Skuperfield("Минималист", City.SAN_KOMARIK, 100000.0);
        System.out.println("Скупёрфильд: " + skuperfield.getName());
        System.out.println("Деньги: " + skuperfield.getMoney());
        System.out.println();

        Barge singleBarge = new Barge("Одиночка", City.DAVILON);
        System.out.println("Баржа: " + singleBarge.getName() + " в " + singleBarge.getCity());
        System.out.println();

        Factory smallFactory = new Factory("Минизавод", City.GRABENBERG, "гвозди", 500, 50);
        System.out.println("Фабрика: " + smallFactory);
        System.out.println();

        Stock cheapStock = new Stock("Микробанк", "гигантская", 50, "перспективная");
        System.out.println("Акция: " + cheapStock);
        System.out.println();

        Worker singleWorker = new Worker("Одинокий", singleBarge, "грузчик");
        System.out.println("Работник: " + singleWorker.getName() + " на барже " + singleBarge.getName());
        System.out.println();

        singleBarge.addStock(cheapStock);
        System.out.println("Акция добавлена на баржу");
        System.out.println();

        skuperfield.addFactory(smallFactory);
        skuperfield.addBarge(singleBarge);
        System.out.println("Фабрика и баржа добавлены");
        System.out.println();


        System.out.println("=== НАЧАЛО ТЕСТА ===");


        try {
            ParagraphOne p1 = new ParagraphOne();
            p1.buildingBarges(skuperfield.getBarges());
            p1.connectingBarges(skuperfield.getBarges().size());

            if (!skuperfield.getBarges().isEmpty()) {
                p1.informationAboutBarges(skuperfield.getBarges(), 0, 0);
            }
        } catch (Exception e) {
            System.out.println("Ошибка в ParagraphOne: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        try {
            ParagraphTwo p2 = new ParagraphTwo(skuperfield, cheapStock);
            p2.ordering();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка в ParagraphTwo: некорректный индекс - " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Ошибка в ParagraphTwo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка в ParagraphTwo: " + e.getMessage());
        }

        try {
            ParagraphThree p3 = new ParagraphThree();
            p3.producing(skuperfield);
        } catch (NullPointerException e) {
            System.out.println("Ошибка в ParagraphThree: null reference - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка в ParagraphThree: " + e.getMessage());
        }
    }
}