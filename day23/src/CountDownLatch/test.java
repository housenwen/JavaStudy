package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class test {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        ThreadA a = new ThreadA(latch);
        ThreadB b = new ThreadB(latch);

        a.start();
        b.start();

    }


    static class ThreadA extends Thread{

        private CountDownLatch latch;

        public ThreadA() {
        }

        public ThreadA(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run(){
            System.out.println("A");
            try {
                latch.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("C");
        }
    }
    static class ThreadB extends Thread{

        private CountDownLatch latch;

        public ThreadB() {
        }

        public ThreadB(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run(){

            System.out.println("B");
            latch.countDown();

        }

    }
}
