package ClassWork06;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class test03 {
    public static void main(String[] args) throws InterruptedException {
//        Map<Integer,Integer> map = new ConcurrentHashMap<>();
//        Map<Integer,Integer> map = new Hashtable<>();
        Map<Integer, Integer> map = new HashMap<>();

        Thread t1 = new Thread() {

            @Override

            public void run() {

                for (int i = 1; i <= 1000000; i++) {
                    map.put(i, i);

                }
//                System.out.println(map);

            }

        };

        t1.start();
        t1.join();
        System.out.println(map.size());

        Thread t2 = new Thread() {

            @Override

            public void run() {

                for (int i = 10001; i <= 2000000; i++) {
                    map.put(i, i);

                }
//                System.out.println(map);

            }

        };

        t2.start();
        t2.join();
        System.out.println(map.size());

    }
}
