package Callable;

public class work1 {
    public static void main(String[] args) {
        final Object A = new Object();
        final Object B = new Object();
        new Thread() {
            @Override
            public void run() {
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName()+"获得锁A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"尝试获得锁B...");
                    synchronized (B) {
                        System.out.println(Thread.currentThread().getName()+"获得锁B");
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName()+"获得锁B");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"尝试获得锁A...");
                    synchronized (A) {
                        System.out.println(Thread.currentThread().getName()+"获得锁A");
                    }
                }
            }
        }.start();
    }
}
