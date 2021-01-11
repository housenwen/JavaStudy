package Thread;

/**
 * 请使用“匿名Thread”子类的方式实现线程，线程中计算1--100所有数字的累加和，并打印计算结果。
 * 请使用“匿名Runnable”子类的方式实现线程，线程中计算1--500所有数字的累加和，
 * 并打印计算结果请使用“匿名Runnable”子类的方式实现线程，线程中计算1--500所有数字的累加和，并打印计算结果
 */
public class test07 {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"线程中执行" + i);
                }
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"线程中执行：" + i);
                }
            }
        }).start();
    }

}
