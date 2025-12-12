package model.entities;

import model.abstractClasses.AbstractEntity;
import model.enums.City;
import model.exceptions.NoMoneyException;
import model.exceptions.StockNotFoundException;
import model.exceptions.WorkerNotFoundException;
import model.market.Stock;
import model.records.TradeOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Skuperfield extends AbstractEntity {
    private double money;
    private ArrayList<Factory> factories = new ArrayList<>();
    private ArrayList<Barge> barges = new ArrayList<>();
    private Map<Stock, Integer> portfolio = new HashMap<>();

    public Skuperfield(String name, City location, double money) {
        super(name, location.getName());
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money){ this.money

            = money;}

    public ArrayList<Barge> getBarges() {
        return new ArrayList<>(barges);
    }

    public ArrayList<Factory> getFactories(){ return new ArrayList<>(factories);}

    public Map<Stock, Integer> getPortfolio() {
        return new HashMap<>(portfolio);
    }

    public void buyIfWorth(Barge barge, Stock stock, int quantity) {
        try {
            ArrayList<Worker> workers = barge.getWorkers();
            Worker worker = workers.get(new Random().nextInt(workers.size()));
            if (barge == null || worker == null || stock == null) {
                throw new IllegalArgumentException("Параметры не могут быть null");
            }

            if (quantity <= 0) {
                throw new IllegalArgumentException("Количество должно быть положительным");
            }

            double currentPrice = stock.getPrice();
            double totalCost = currentPrice * quantity;

            System.out.printf("%n%s рассматривает покупку %d акций '%s' по %.2f тенге за штуку (всего: %.2f)%n",
                    name, quantity, stock.getName(), currentPrice, totalCost);

            if (!stock.isWorthBuying(this)) {
                System.out.printf("%n%s решил не покупать акцию '%s', так как она не выглядит перспективной%n",
                        name, stock.getName());
                return;            }

            if (totalCost > money) {
                System.out.printf("%n%s не может купить %d акций '%s': нужно %.2f тенге, а есть только %.2f%n",
                        name, quantity, stock.getName(), totalCost, money);
                return;
            }
            barge.call(barge.getConnections().get(new Random().nextInt(barge.getConnections().size())), "К нам едет босс.");
            this.goToBarge(barge);

            TradeOrder tradeOrder = new TradeOrder(stock, quantity, currentPrice);

            TradeOrder validatedOrder = barge.executeOrder(worker, tradeOrder);

            if (validatedOrder != null && validatedOrder.isValid()) {
                money -= totalCost;

                worker.work(tradeOrder, this);

                System.out.printf("%nбаланс счета %s изменен: %s тенге.", name, getMoney());

                portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);
            }

        } catch (WorkerNotFoundException e) {
            System.out.printf("Работник не найден: %s%n", e.toString());
        } catch (StockNotFoundException e) {
            System.out.printf("Акция не найдена на барже: %s%n", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.printf("Некорректные параметры: %s%n", e.getMessage());
        } catch (Exception e) {
            System.out.printf("Не удалось купить акцию '%s': %s%n",
                    stock != null ? stock.getName() : "неизвестная", e.getMessage());
        }
    }

    public void goToBarge(Barge barge) {
        System.out.printf("%s отправился на баржу %s, с целью покупки акции.", name, barge.getName());
    }

    public void goToFactory(Factory factory) {
        System.out.print(name + " отправился на фабрику " + factory.getName());
    }

    public void addBarge(Barge barge) {
        barges.add(barge);
    }

    public void addFactory(Factory factory) {
        factories.add(factory);
    }

    @Override
    public String toString(){
        return String.format("Skuperfield[name: %s, location: %s, money: %s, factories: %s," +
                "barges: %s, portfolio: %s]", name, location, money, factories, barges, portfolio);
    }
}