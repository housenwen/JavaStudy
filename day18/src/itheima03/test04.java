package itheima03;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

//     请按以下要求顺序编码：
//
//Ø  定义一个可以存储“整数”的LinkedHashSet对象
//
//Ø  存储以下整数
//
//20,30,50,10,30,20
//
//Ø  打印集合大小。为什么跟存入的数量不一致？
//
//Ø  使用增强for遍历集合，打印大于25的元素
public class test04 {
    public static void main(String[] args) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Collections.addAll(set,20,30,50,10,30,20);
        System.out.println("集合的大小："+set.size());

        System.out.println(set);
        System.out.println("____________________________________");
        for (int a :set){

            if (a>25){
                System.out.println("大於25的元素為："+a);
            }

        }

    }
}
