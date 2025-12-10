package scenario;

import model.entities.Barge;
import model.entities.Worker;

import java.util.ArrayList;
import java.util.Random;

public class ParagraphOne {
    public void buildingBarges(ArrayList<Barge> barges){
        if (barges.size() == 2){
            System.out.printf(" В скором времени %s, а затем %s.", barges.get(0).build(), barges.get(1).build());
        } else {
            System.out.printf(" В скором времени %s, а затем %s, ", barges.get(0).build(), barges.get(1).build());
            for (int i = 0; i < barges.size(); i++){
                System.out.printf(barges.get(i).build());
            }
        }
    }

    public void connectingBarges(int countOfBarges){
        System.out.printf("%nКогда изобрели телефон, все %d баржи были соединены между собой телефонными проводами. ", countOfBarges);
    }

    public void informationAboutBarges(ArrayList<Barge> barges, int startBarge, int worker) throws IndexOutOfBoundsException{
        if (barges.isEmpty()) return;

        Barge barge = barges.get(startBarge);
        ArrayList<Worker> workers = barge.getWorkers();

        if (workers.isEmpty()) return;

        String type = workers.get(worker).getType();
        ArrayList<Barge> connections = barge.getConnections();

        if (connections.isEmpty()) return;

        int randomBargeNumber = new Random().nextInt(connections.size());

        System.out.printf("и %s с баржи %s в любое время мог узнать о положении дел на барже %s.",
                type, barge.getName(), connections.get(randomBargeNumber).getName());
    }
}