package ClassWork01;

import java.util.Random;

public class RunnableImp implements Runnable{
    @Override
    public void run() {
        int sum = 0;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {

            sum+=random.nextInt(100);

        }

        System.out.println(Thread.currentThread().getName()+"线程的和值："+sum);
    }
}
