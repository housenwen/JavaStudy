package Callable;

public class work7 {
    public static void main(String[] args) {
        // 创建抽奖任务,实现了Runnable接口
        LuckDraw ld = new LuckDraw();
        // 在主线程中开启两个线程,表示前门和后门
        // 传入实现Runnable接口的实现类对象
        Thread t1 = new Thread(ld, "前门");
        Thread t2 = new Thread(ld, "后门");
        // 开启两个线程
        t1.start();
        t2.start();
    }
}