package itheima03;

import java.util.Collections;
import java.util.TreeSet;

//  请按以下要求顺序编码：
//Ø  定义一个可以存储“整数”的TreeSet对象
//Ø  存储以下整数
//30,20,50,10,30,20
//Ø  打印集合大小。为什么跟存入的数量不一致？
//Ø  遍历集合，打印大于25的元素
public class Test05 {
    public static void main(String[] args) {

        TreeSet<Integer>  set = new TreeSet<>();
        Collections.addAll(set,30,20,50,10,30,20);
        System.out.println("size="+set.size());
        for (int a:set){
            if (a>5){
                System.out.println(a);
            }
        }

        System.out.println("--------------------------");
        System.out.println(set);
    }
}
