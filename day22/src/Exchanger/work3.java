package Exchanger;

import java.util.concurrent.Exchanger;

public class work3 {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA a = new ThreadA(exchanger);
        a.start();
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("还剩"+i+"秒");
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        ThreadB b = new ThreadB(exchanger);
        b.start();
    }
}
