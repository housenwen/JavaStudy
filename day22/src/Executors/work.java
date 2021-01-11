package Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class work {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);

        MyRunnable m = new MyRunnable();

//        Thread thread = new Thread(m);
//        Future f1 = service.submit(m);
//        Future f2 = service.submit(m);

        service.submit(m);
        service.submit(m);

//      service.submit(m);

        service.shutdown();


    }
}
