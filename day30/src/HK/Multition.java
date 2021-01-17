package HK;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Multition {
    private static final int maxCount = 3;
    private static List instanceList = new ArrayList();
    private Multition(){

    }
    static {
        for (int i = 0; i < maxCount; i++) {
            Multition multition = new Multition();
            instanceList.add(multition);
        }
    }
    public static Multition getMultition(){
        Random r = new Random();
        Multition multition =(Multition) instanceList.get(r.nextInt(maxCount));
        return multition;
    }
}
