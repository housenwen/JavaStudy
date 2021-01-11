package Executors;

public class Demo5 {
    public static void main(String[] args) {
        MyRunnableImpl mr = new MyRunnableImpl();

        new Thread(mr).start();
        new Thread(mr).start();
    }
}


