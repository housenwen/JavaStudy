package ClassWork01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountThread extends Thread{
    private int num;

//    Lock lock = new ReentrantLock();
//    Object lock = new Object();
    public CountThread() {
    }

    public CountThread(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    @Override
    public void run(){

        int sum = 1;

        for (int i = num; i > 0; i--) {

            sum=sum*i;
        }
//        int sum = 1;
//        // 计算num的阶乘
//        for (int i = num; i >= 1; i--) {
//            sum *= i;
//        }
        // 获得当前线程
        System.out.println(Thread.currentThread().getName()+"线程的阶段乘："+sum);

    }
}
