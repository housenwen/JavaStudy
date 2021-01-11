package SafeThread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test02 {
    public static void main(String[] args) throws InterruptedException {

//        for (int i = 5; i >= 0; i--) {
//            System.out.println("还剩"+i+"秒");
//            Thread.sleep(1000);//让所有线程执行完毕
//        }
//        System.out.println("原子弹轰炸东京");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        for (int i = 0; i < 10; i++) {
            Date date = new Date();

            String dateStr  = sdf.format(date);
            System.out.println("现在是北京时间："+dateStr);

            Thread.sleep(1000);

        }

    }
}
