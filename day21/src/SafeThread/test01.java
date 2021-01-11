package SafeThread;

public class test01 {
    public static void main(String[] args) throws InterruptedException {
        //1.启动两个线程
        CThread t1 = new CThread();
        CThread t2 = new CThread();

        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println("获取a最终值：" + CThread.a);//总是不准确的。原因：两个线程访问a的步骤不具有：原子性

    }
}
