package ClassWork;

import java.util.ArrayList;

public class work7 {
    public static void main(String[] args) throws InterruptedException {

        Hall hall = new Hall();
        ArrayList<Thread> windows = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            Thread.sleep(10);
            Thread window = new Thread(hall,"t"+i);
            windows.add(window);
            window.start();
        }

        for (Thread window:windows){
            window.join();
        }
        hall.check();
    }
}
