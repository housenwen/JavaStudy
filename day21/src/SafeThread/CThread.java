package SafeThread;

import java.util.concurrent.atomic.AtomicInteger;

public class CThread extends Thread {
    //    public static volatile int a = 0;
    public static AtomicInteger a = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
//            a++;
            a.getAndIncrement();//先获取，再自增1：a++
        }
        System.out.println("修改完毕！");
    }
}
