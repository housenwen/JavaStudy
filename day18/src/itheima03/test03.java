package itheima03;

import java.util.*;
//Ø  定义一个Set集合，并存储以下数据：
//
//        刘备，关羽，张飞，刘备，张飞
//
//        Ø  打印集合大小
//
//        Ø  使用迭代器遍历集合，并打印每个元素
//
//        Ø  使用增强for遍历集合，并打印每个元素

public class test03 {
    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();
        Collections.addAll(set,"劉備","關羽","張飛","劉備","張飛");

        System.out.println("集合set的長度"+set.size());

        System.out.println("集合set的元素為："+set);
        System.out.println("------------------------------");
        Iterator it = set.iterator();
        while (it.hasNext()){

            System.out.println(it.next());
        }
        System.out.println("------------------------------");
        for (String str:set){
            System.out.println(str);
        }

    }
}
