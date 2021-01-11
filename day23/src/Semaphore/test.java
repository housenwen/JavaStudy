package Semaphore;

import java.util.concurrent.Semaphore;

public class test {
    public static void main(String[] args) {
        Service service = new Service();

        for (int i = 0; i < 5; i++) {
            ThreadA a = new ThreadA(service);

            a.start();
        }
    }
    static class Service {
        private Semaphore semaphore = new Semaphore(1);
        //TODO 1表示许可的意思，表示最多允许1个线程执行acquire()和release()之间的内容,可修改数值

        public void testMethod() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"进入时间"
                        +System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"结束时间"
                +System.currentTimeMillis());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    static class ThreadA extends Thread{
        private Service service;
        public ThreadA(Service service) {
            this.service = service;
        }
        @Override
        public void run(){
            service.testMethod();
        }

    }
}
