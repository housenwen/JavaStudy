package TwoColorBall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        LinkedHashSet<Integer> setRed = new LinkedHashSet<>();
        Random r = new Random();
        while (setRed.size()<6){
            setRed.add(r.nextInt(33)+1);
        }
        System.out.println("红色球");
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(setRed);
        Collections.sort(list);
        System.out.println(list);

        Integer buleBall = r.nextInt(16)+1;
        System.out.println("蓝色球：");
        System.out.println(buleBall);

    }
}
