package Timer;

import java.util.Timer;

public class work05 {
    public static void main(String[] args) {
        Timer timer= new Timer();
        BTimerTask b = new BTimerTask();
        timer.schedule(b,3000,1000);
    }
}
