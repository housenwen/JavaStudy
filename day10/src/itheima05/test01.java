package itheima05;

import java.util.ArrayList;

//1、请按以下要求编写代码：
//
//	1.        定义一个只能存储字符串的集合对象；
//	2.        向集合内添加以下数据：
//      	 “孙悟空”
//       	 “猪八戒”
//       	 “沙和尚”
//      	 “铁扇公主”
//	3.        不用遍历，直接打印集合；
//	4.        获取第4个元素（注意，是--第4个元素，它的索引是？）
//	5.        打印一下集合大小；
//	6.        删除元素“铁扇公主”
//	7.        删除第3个元素（注意：是--第3个元素）
//	8.        将元素“猪八戒”改为“猪悟能”
//9.  再次打印集合；
public class test01 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String s1 = "孙悟空";
        String s2 = "猪八戒";
        String s3 = "沙和尚";
        String s4 = "铁扇公主";

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        System.out.println(list);

        String str = list.get(3);
        System.out.println(str);

        int l = list.size();
        System.out.println(l);

        list.remove("铁扇公主");
        System.out.println(list);

        list.remove(2);
        System.out.println(list);

        list.set(1,"猪悟能");
        System.out.println(list);

    }
}
