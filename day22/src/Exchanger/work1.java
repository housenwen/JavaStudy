package Exchanger;

import java.util.concurrent.Exchanger;

/**Exchanger（交换者）是一个用于线程间协作的工具类。
 *
 * Exchanger用于进行线程间的数据交换。

 这两个线程通过exchange方法交换数据，如果第一个线程先执行exchange()方法，

 它会一直等待第二个线程也执行exchange方法，当两个线程都到达同步点时，

 这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。

 */
public class work1 {
    public static void main(String[] args) throws InterruptedException {

        Exchanger<String> exchanger = new Exchanger<String>();

//        while (true) {
            ThreadA a = new ThreadA(exchanger);
            ThreadB b = new ThreadB(exchanger);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            a.start();
            b.start();
        }
    }
//}
