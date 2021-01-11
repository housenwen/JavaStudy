package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);

        MyRunnable m = new MyRunnable("张三");

        service.submit(new MyRunnable("赵四"));
        service.submit(new MyRunnable("周杰伦"));
        service.submit(new MyRunnable("双杰伦"));
        service.submit(new MyRunnable("李连杰"));
        service.submit(m);

    }
    static class MyRunnable implements Runnable{

        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("我需要一个教练");
            try{
                Thread.sleep(1000*2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"教练过来了。。。");
            System.out.println("教练教完我游泳，又走了");
        }
    }
}
