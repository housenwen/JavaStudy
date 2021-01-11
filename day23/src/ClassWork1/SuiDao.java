package ClassWork1;

public   class SuiDao implements Runnable{

    private int person = 0;



    @Override
    public synchronized void run() {

        String name = Thread.currentThread().getName();


                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                person++;
                System.out.println(name+"从隧道过去了,ta是第"+person+"名");

    }
}
