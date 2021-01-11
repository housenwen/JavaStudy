package Synchronized.com;

public class test01 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();

        Thread.sleep(1000);

        System.out.println("最终集合的长度：" + MyThread.list.size());
    }
}
