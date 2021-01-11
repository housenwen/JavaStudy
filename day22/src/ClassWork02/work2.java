package ClassWork02;

//线程安全问题都是由全局变量及静态变量引起的。
// 而每个线程操作这个变量都需要很多步骤：获取变量的值、打印变量的值、更改变量的值，
// 而一个线程在执行某一步骤时都可能被暂停，而另一个线程会执行，
// 这同样会导致多个线程访问同一个变量，最终导致这个变量的值不准确。

public class work2 {
    public static void main(String[] args) {
//创建线程任务对象
        Bill bill = new Bill();
//创建三个窗口对象
        Thread thread1 = new Thread(bill,"窗口1");
        Thread thread2 = new Thread(bill,"窗口2");
        Thread thread3 = new Thread(bill,"窗口3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
