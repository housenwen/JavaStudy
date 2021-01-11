package ClassWork06;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class test01 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList<>();

        Thread t1 = new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
            }
        };

        t1.start();
        t1.join();
        System.out.println(list.size());

        Thread t2 = new Thread(){
            @Override
            public void run(){
                for (int i = 10000; i < 20000; i++) {
                    list.add(i);
                }
            }
        };
        t2.start();
        t2.join();

        System.out.println(list.size());
    }
}
