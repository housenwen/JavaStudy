package ClassWork05;

import java.text.SimpleDateFormat;
import java.util.Date;

/**模拟实现一个定时器，每隔1秒输出一下表示当前时间的字符串
 *
 */
public class work {
    public static void main(String[] args) throws InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



        while (true){

            Date date = new Date();

            System.out.println();

            System.out.println(sdf.format(date));

            Thread.sleep(1000);

        }

    }
}
