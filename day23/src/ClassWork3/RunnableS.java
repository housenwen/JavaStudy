package ClassWork3;

public class RunnableS implements Runnable {

   private int num = 0;

    @Override
    public void run() {
        synchronized (this) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num++;
                System.out.println(Thread.currentThread().getName()+"过去了他是第"+num+"通过的");

            }

        }

    }

