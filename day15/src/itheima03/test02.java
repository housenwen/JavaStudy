package itheima03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class test02 {
    public static void main(String[] args) {

        List<Integer> coll = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            coll.add(i);
        }

        for (int i = 0; i < coll.size(); i++) {
            System.out.println(coll.get(i));
        }

        System.out.println("------------------");
        //使用迭代器 遍历   每个集合对象都有自己的迭代器
        Iterator it = coll.iterator();
        while (it.hasNext()){ //判断是否有迭代元素
            int x = (int) it.next();//获取迭代出的元素
            System.out.println(x);

        }
    }
}
