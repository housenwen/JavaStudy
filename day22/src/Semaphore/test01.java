package Semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * 请使用Semaphore编写一个程序，实现以下效果：有10名游客要参观展览室，
 * <p>
 * 而“展览室”同时只允许最多“三个游客”参观，每个游客参观时间2秒。
 */
public class test01 {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {

            new Thread() {
                @Override
                public void run() {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "开始参观" + sdf.format(new Date()));
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }

            }.start();

        }
    }
}
