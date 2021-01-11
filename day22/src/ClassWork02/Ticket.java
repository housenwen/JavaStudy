package ClassWork02;

/**
 * 请使用“同步代码块”改写1.1的程序，保证运行结果的正确。
 */
public class Ticket implements Runnable{

    private int ticket = 100;
    final Object lock = new Object();

    @Override
    public void run(){
        int sellTicket = 0;
        while (true){

            synchronized (lock){

                if (ticket>0){

                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    ticket--;
                    sellTicket++;

                    System.out.println(Thread.currentThread().getName()+"已卖："+sellTicket+"还剩："+ticket);

                    if (ticket<0){
                        break;
                    }
                }
            }

        }


    }
}
