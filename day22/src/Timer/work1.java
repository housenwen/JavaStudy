package Timer;


import java.util.Timer;

public class work1 {
    public static void main(String[] args) {

        ATimerTask a = new ATimerTask();

        Timer t = ATimerTask.t;

        t.schedule(a,2000);

    }
}
