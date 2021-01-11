package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class test {
    public static void main(String[] args) {

        CyclicBarrier cyc = new CyclicBarrier(5,new BThread());

        PThread p1 = new PThread(cyc);
        PThread p2 = new PThread(cyc);
        PThread p3 = new PThread(cyc);
        PThread p4 = new PThread(cyc);
        PThread p5 = new PThread(cyc);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

    }
    static class PThread extends Thread{

        private CyclicBarrier cb;

        public PThread(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override

        public void run(){
            try {
                Thread.sleep((int) (Math.random()*1000));
                System.out.println(Thread.currentThread().getName()+"到了");
                cb.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }

        }
    }

    static class BThread extends Thread{
        @Override
        public void run(){
            System.out.println("人都齐了，开会！");
        }
    }
}
