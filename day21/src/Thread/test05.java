package Thread;

public class test05 {
    public static void main(String[] args) {

        ChildThread childThread = new ChildThread();
        childThread.start();
        ChildThread1 childThread1 = new ChildThread1();
        childThread1.start();
        CThread cThread = new CThread();
        new Thread(cThread).start();
    }

    static class ChildThread extends Thread{

        public ChildThread() {
        }

        @Override
        public void run(){

            int sum = 0;

            for (int i = 0; i <= 100; i++) {
                sum+=i;
            }
            System.out.println(Thread.currentThread().getName()+"线程计算1-100的和："+sum);
        }

    }
    static class ChildThread1 extends Thread{

        public ChildThread1() {
        }

        @Override
        public void run(){

            int sum = 0;

            for (int i = 0; i <= 10; i++) {
                sum+=i;
            }
            System.out.println(Thread.currentThread().getName()+"线程计算1-10的和："+sum);
        }

    }
   static class CThread implements Runnable {
        public CThread() {
        }

        @Override
        public void run() {
            int sum = 0;

            for (int i = 0; i <= 1000; i++) {
                sum+=i;
            }
            System.out.println(Thread.currentThread().getName()+"线程计算1-1000的和："+sum);
        }
        }

    }


