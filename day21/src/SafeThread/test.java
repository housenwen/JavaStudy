package SafeThread;

public class test {
    public static void main(String[] args) {
        //1.启动线程
        MyThread myThread = new MyThread();
        myThread.run();

        //2.主线程继续
        while (true) {
            if (MyThread.a == 1) {
                System.out.println("主线程读到了a = 1");
            }
        }
    }
}
