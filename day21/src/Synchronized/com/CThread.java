package Synchronized.com;

import java.util.concurrent.CopyOnWriteArrayList;

public class CThread extends Thread {
    public static CopyOnWriteArrayList<Integer> set = new CopyOnWriteArrayList<>();

    @Override
    public void run(){
        for (int i = 0; i < 10000; i++) {

            set.add(i);

        }
        System.out.println("添加完毕！");
    }
}
