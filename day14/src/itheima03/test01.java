package itheima03;

import java.text.SimpleDateFormat;
import java.util.Date;
//请在控制台以“xxxx年xx月xx日 xx时xx分xx秒”的格式打印当前系统时间。
public class test01 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        String date = sdf.format(new Date());

        System.out.println(date);


    }
}
