package Callable;

public class work6 {
    public static void main(String[] args) throws InterruptedException {

        MyRunnable mr = new MyRunnable();

        Thread t1 = new Thread(mr,"后门");
        Thread t2 = new Thread(mr,"前门");

        t1.start();
        t2.start();
//        t1.join();
//        t2.join();

    }
}
