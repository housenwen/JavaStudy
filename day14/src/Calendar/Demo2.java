package Calendar;

import java.util.Calendar;

public class Demo2 {
    public static void main(String[] args) {
        //设置属性——set(int field,int value):
        Calendar c1 = Calendar.getInstance();//获取当前日期

        //计算班长出生那天是星期几(假如班长出生日期为：1998年3月18日)
        c1.set(Calendar.YEAR, 1998);
        c1.set(Calendar.MONTH, 3 - 1);//转换为Calendar内部的月份值
        c1.set(Calendar.DAY_OF_MONTH, 18);


        int w = c1.get(Calendar.DAY_OF_WEEK);
        System.out.println("班长出生那天是：" + getWeek(w));


    }
    //查表法，查询星期几
    public static String getWeek(int w) {//w = 1 --- 7
        //做一个表(数组)
        String[] weekArray = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        //            索引      [0]      [1]       [2]      [3]       [4]      [5]      [6]
        //查表
        return weekArray[w - 1];
    }
}
