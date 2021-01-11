package ClassWork1;
/**请用“等待唤醒”机制编写一个程序，要求：
 第一个线程：遍历1--1000所有的数字，在遍历过程中，如果发现这个数字能同时被
 2,3,5,7整除，立即wait()退出等待，让第二个线程进入。
 第二个线程：运行后，将一个计数器 + 1，之后再唤醒等待的线程。
 主线程中：休息2秒，让两个线程全部执行完毕，打印“计数器”的结果。
 注意：第二个线程使用的计数器，要定义在线程外部。
*/
 public class work3 {

    final static Object lock = new Object();

    static int count = 0;//计数
    static int flag = 0;//控制计时器

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 1; i < 1000; i++) {
                        while (flag == 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (i % 2 == 0 && i % 3 == 0 && i % 5 == 0 && i % 7 == 0) {
                            System.out.println(i);
                            flag = 1;
                            lock.notify();
                        }
                    }
                }
                synchronized (lock) {
                    flag = 2;
                    lock.notify();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {
                        while (flag == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (flag == 2) {

                            break;
                        } else if (flag == 1) {
                            count++;
                            flag = 0;
                            lock.notify();
                        }
                    }
                }
            }
        }.start();

        Thread.sleep(2000);
        System.out.println(count);
    }
}
