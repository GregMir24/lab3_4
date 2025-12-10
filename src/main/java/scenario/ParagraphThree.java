package scenario;

import model.entities.Factory;
import model.entities.Skuperfield;

import java.util.ArrayList;
import java.util.Random;

public class ParagraphThree {
    public void producing(Skuperfield skuperfield){
        ArrayList<Factory> factories = skuperfield.getFactories();
        System.out.printf("К концу дня %s решил поднять бабла и получить прибыль с фабрик.", skuperfield.getName());
        if (skuperfield.getFactories().size()>1){
            System.out.printf("%n%s отправился на фабрики:", skuperfield.getName());
            for (int i = 0; i < skuperfield.getFactories().size(); i++) {
                System.out.print(skuperfield.getFactories().get(i));
            }
        }else{
            skuperfield.goToFactory(skuperfield.getFactories().get(0));
        }
        for (int i = 0; i < factories.size(); i++){
            factories.get(i).produce();
            factories.get(i).sell(new Random().nextInt(factories.get(i).getStorage()), skuperfield);
        }
        System.out.printf("%nТеперь состояние %s составляет %s", skuperfield.getName(), skuperfield.getMoney());
    }


}
