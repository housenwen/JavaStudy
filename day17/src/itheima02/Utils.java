package itheima02;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static int stuId;
    public static int teaId;

    static {

        stuId = 0;
        teaId = 0;
    }
    public static int birthdayToAge(String birthday){

        Date birthDate = null;

        try{
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

        Calendar calendar = Calendar.getInstance();

        if (calendar.before(birthDate)){
            return -1;
        }

        int yearNow = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH+1);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//将日期设置为出生日期
        calendar.setTime(birthDate);
        int yearBirth = calendar.get(Calendar.YEAR);
        int monthBirth = calendar.get(Calendar.MONTH+1);
        int dayOfBirth = calendar.get(Calendar.DAY_OF_MONTH);

        //当前年份与出生年份相减，初步计算年龄
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄减1，表示不满多少周岁
        int age = yearNow -yearBirth;

        if (monthBirth>=month) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthBirth == month) {
                if (dayOfBirth > dayOfMonth) age--;

            }else {
                age--;
            }
        }
        return age;

    }
    //打印ArrayList的方法
    public static void printPersonList(ArrayList personList){
        System.out.println("*******************************************************");
        System.out.println("编号\t\t姓名\t\t性别\t\t生日\t\t\t年龄\t\t描述");
        for (int i = 0; i < personList.size(); i++) {
            Object p = personList.get(i);
            System.out.println(personList.get(i));
        }
        System.out.println("*******************************************************");

    }

    //打印Person的方法
    public static void printPerson(Person person) {
        System.out.println("*******************************************************");
        System.out.println("编号\t\t姓名\t\t性别\t\t生日\t\t\t年龄\t\t描述");
        System.out.println(person );
        System.out.println("*******************************************************");
    }

}
