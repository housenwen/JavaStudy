package SafeThread;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new MmThread().start();//创建1000个线程，每个线程为数组的每个元素+1
        }
        System.out.println("主线程休息5秒醒来");

        MmThread mmThread = new MmThread();

        for (int i = 0; i < MmThread.arr.length(); i++) {
            System.out.println(MmThread.arr.get(i));
        }

    }
}
