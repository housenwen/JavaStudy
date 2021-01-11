package Callable;

public class work8 {
    public static void main(String[] args) {

        TRunnable t = new TRunnable();
        Thread t1 = new Thread(t,"前门");
        Thread t2 = new Thread(t,"后门");
        t1.start();
        t2.start();

    }
}
