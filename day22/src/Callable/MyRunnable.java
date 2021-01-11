package Callable;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable {

    private  int number = 100;
    //    private static Map<Integer,String> map = new Hashtable<>();
    private  Map<Integer, String> map = new ConcurrentHashMap<>();
    DoubleColorBallUtil ballUtil = new DoubleColorBallUtil();

    final Object lock = new Object();
    Lock lock1 = new ReentrantLock();
    int count = 0;

    {
        for (int i = number; i > 0; i--) {
            map.put(i, ballUtil.create());
        }
    }
    @Override
    public void run() {
//        synchronized (lock) {
            Set<Integer> set = map.keySet();
            for (Integer i : set) {
                System.out.println("编号" + i + "的员工从" + Thread.currentThread().getName()
                        + "入场" + "拿到的双色球号码是：" + map.get(i));
                map.remove(i);
                count++;

                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "入场人数：" + count);
        }
    }
//}