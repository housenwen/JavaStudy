package itheima03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

//1.1 题目
//
//              请定义一个可以存储“整数”的集合，并存储一些数据。
//
//              请按以下要求顺序编程实现：
//
//Ø  对集合中的数据打乱顺序；
//
//Ø  打印集合
//
//Ø  对集合中的数据进行升序排序
//
//Ø  打印集合
//
//Ø  对集合中的数据进行降序排序(要实现排序，不可以倒序遍历实现)
//
//Ø  打印集合
public class test02 {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        Collections.addAll(list,1,2,3,4,5,6,7,8,9);

        System.out.println("初始顺序："+list);
//打乱排序
        Collections.shuffle(list);
        System.out.println("打乱后的排序"+list);
//        升序排序；
        Collections.sort(list);
        System.out.println("升序排序"+list);

//        降序排序；
        //定义比较器
        Comparator<Integer> desc = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        };
        Collections.sort(list,desc);

        System.out.println("倒序后的排序為："+list);

    }
}
