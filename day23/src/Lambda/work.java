package Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class work {
    public static void main(String[] args) {

        Random r = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(r.nextInt(100)+1);
        }
        System.out.println(list);
        Collections.sort(list,(o1,o2) -> {
            return o2-o1;
        });
        System.out.println(list);
    }
}
