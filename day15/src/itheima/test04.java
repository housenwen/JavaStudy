package itheima;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test04 {
    public static void main(String[] args) throws ParseException {

        String datestr = "2000-1-1";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse(datestr);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");

        String  result = sdf2.format(date);

        System.out.println(result);

    }
}
