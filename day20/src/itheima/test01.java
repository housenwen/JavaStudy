package itheima;
/**
 *
 *
 * 第一种双色球开奖计算方式
 * 使用Set集合去除重
 *
 */
import java.util.*;

public class test01 {
    public static void main(String[] args) {

        TreeSet<Integer> setRed = new TreeSet<>();
        Random r = new Random();

        while (setRed.size()<6){

            int i = r.nextInt(32)+1;

            setRed.add(i);

        }

        System.out.println(setRed);

        ArrayList<String> list = new ArrayList<>();

        for (Integer integer:setRed){


            if (integer<10){

                list.add("0"+integer+"");

            }else {

                list.add(integer+"");
            }

        }
        System.out.println(list);

        Integer buleBall = r.nextInt(16)+1;

        if (buleBall<10){

            list.add("0"+buleBall+"");


        }else {

            list.add(buleBall+"");
        }

        System.out.println("本期中奖号码是："+list);


    }
}
