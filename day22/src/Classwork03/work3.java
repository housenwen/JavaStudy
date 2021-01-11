package Classwork03;

import java.util.concurrent.*;

public class work3 {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);

        MyRunnable r = new MyRunnable();

        // 再获取个线程对象，调用MyRunnable中的run()
        service.submit(r);
        service.submit(r);

//        Thread t1 = new Thread(r,"王刚");
//        Thread t2 = new Thread(r,"刘德华");
//
//        Future f1 = service.submit(t1);
//        Future f2 = service.submit(t2);
//
//        try{
//            f1.get();
//            f2.get();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }catch (ExecutionException e){
//            e.printStackTrace();
//        }

        service.shutdown();
    }
}
