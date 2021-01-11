package Synchronized;
//当使用了同步代码块后，线程的安全问题，解决了。
public class Ticket implements Runnable {

    private int ticket = 100;
    Object lock = new Object();

    @Override
    public void run() {
        //每个窗口卖票的操作
        //窗口 永远开启

        while (true) {
            synchronized (lock) {
                if (ticket > 0) {
                    //有票 可以卖
                    //出票操作
                    //使用sleep模拟一下出票时间
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket<0){
                    break;
                }
                //获取当前线程对象的名字
                String name = Thread.currentThread().getName();
                System.out.println(name + "正在卖。。。" + ticket--);
            }

        }
    }
}