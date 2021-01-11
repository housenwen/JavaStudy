package Synchronized.com;

public class Demo {
    public static void main(String[] args) {
        CThread cThread = new CThread();
        cThread.start();

        for (int i = 10000; i < 20000; i++) {
            CThread.set.add(i);
        }
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终集合的长度为："+CThread.set.size());


    }
}
