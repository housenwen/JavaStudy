package Synchronized.com;

public class TaksMap {
    public static void main(String[] args) throws InterruptedException{

//        YThread y = new YThread();
//
//        y.start();
//
//        for (int i = 10000; i < 20000; i++) {
//            YThread.map.put(i,i);
//        }

        for (int i = 0; i < 10000; i++) {
            new YThread().start();
        }
        Thread.sleep(1000*1);

        System.out.println("map的大小："+YThread.map.size());

    }
}
