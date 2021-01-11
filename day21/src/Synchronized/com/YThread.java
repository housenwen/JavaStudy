package Synchronized.com;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YThread extends Thread {
    //    public static Map<Integer,Integer> map = new HashMap<>();
//    public static Map<Integer, Integer> map = new Hashtable<>();
    public static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            map.put(i, i);

        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "毫秒");
    }
}
