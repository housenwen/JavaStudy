package ClassWork3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws InterruptedException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (true){

            Thread.sleep(1000);

            System.out.println(df.format(new Date()));

        }

    }
}
