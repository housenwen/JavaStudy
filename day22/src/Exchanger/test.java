package Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * 请使用Exchanger编写一个程序，实现两个线程的信息交互：
 *
 * 	线程A给线程B：一条娱乐新闻
 *
 * 	线程B给线程A：一条体育新闻
 */
public class test {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName()+"等待交换");
                String result = null;
                try {
                    result = exchanger.exchange("一条娱乐新闻");

                }catch (InterruptedException e){

                    e.printStackTrace();

                }

                System.out.println(Thread.currentThread().getName()+result);

            }
        }.start();
        Thread.sleep(2000);
        new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName()+"等待交换呢");
                String result = null;
                try{
                    result = exchanger.exchange("一条体育新闻");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+result);
            }
        }.start();
    }
}
