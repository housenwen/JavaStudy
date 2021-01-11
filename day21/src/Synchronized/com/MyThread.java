package Synchronized.com;


import java.util.concurrent.CopyOnWriteArrayList;

public class MyThread extends Thread {
//    public static List<Integer> list = new ArrayList<>();//线程不安全的
    public static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        System.out.println("添加完毕！");
    }
}
