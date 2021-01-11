package ClassWork02;

/**
 * 请使用“同步方法”改写1.1的程序，保证运行结果的正确。
 */
public class Ballot implements Runnable{

    private int ballot = 100;


    @Override
    public   void run() {

        sellBall();

    }

    private synchronized void sellBall() {

        int sellBallot = 0;

        while (true){

            if (ballot>0){

                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                sellBallot++;
                ballot--;

                System.out.println(Thread.currentThread().getName()+"已卖"+sellBallot+"还剩"+ballot);

            }else {
                break;
            }

        }
    }
}
