package itheima;

import java.util.Calendar;

public class test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1949,9,1);

        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }
}
