package itheima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * 第三种双色球开奖计算方式
 * Arraylist的remove 方法去重
 *
 */
public class test03 {
    public static void main(String[] args) {

        ArrayList<Integer>  totalList = new ArrayList<>();

        for (int i = 1; i < 33; i++) {
            totalList.add(i);
        }

        System.out.println(totalList);

        ArrayList<Integer> list6 = new ArrayList<>();

        Random r = new Random();

        System.out.println(totalList.size());

        for (int i = 0; i < 6; i++) {

            int index = r.nextInt(totalList.size());

            list6.add(totalList.get(index));

            totalList.remove(index);

        }

        Collections.sort(list6);
        System.out.println(list6);

        Integer blueBall = r.nextInt(16)+1;
        list6.add(blueBall);
        System.out.println(blueBall);
        System.out.println("本期中奖号码："+list6);

    }

}
