package Semaphore;

import java.util.concurrent.Semaphore;

public class Service {
    private Semaphore semaphore = new Semaphore(2);
    //1表示许可的意思，表示最多允许1个线程执行acquire()和release()之间的内容
//    同时允许2个线程同时执行
//
// 修改Service类，将new Semaphore(1)改为2即可：

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()
                    + " 进入 时间=" + System.currentTimeMillis());
            Thread.sleep(1000*2);
            System.out.println(Thread.currentThread().getName()
                    + "   结束 时间=" + System.currentTimeMillis());
            semaphore.release();
            //acquire()和release()方法之间的代码为"同步代码"
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
