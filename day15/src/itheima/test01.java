package itheima;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test01 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        String result = sdf.format(new Date());

        System.out.println(result);
    }
}
