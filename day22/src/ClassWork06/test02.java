package ClassWork06;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class test02 {
    public static void main(String[] args) throws InterruptedException{
        Set<Integer> set  = new CopyOnWriteArraySet<>();

        Thread t1 = new Thread(){

            @Override
            public  void  run(){

                for (int i = 0; i < 10000; i++) {

                    set.add(i);

                }

            }

        };

        t1.start();
        t1.join();

        System.out.println(set.size());
        Thread t2 = new Thread(){

            @Override
            public  void  run(){

                for (int i = 10000; i < 20000; i++) {

                    set.add(i);

                }

            }

        };

        t2.start();
        t2.join();

        System.out.println(set.size());

    }
}
