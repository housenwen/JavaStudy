package itheima06;

//5、定义ArrayList集合，存入多个字符串
//
//	1、 如:"ab1" "123ad"  "bca" "dadfadf"  "dddaaa"  "你好啊"  "我来啦"  "别跑啊"
//
// 	2.遍历集合,删除长度大于5的字符串,打印删除后的集合对象
//
// 	3.基于上一步,删除集合中元素包含0-9数字的字符串(只要字符串中包含0-9中的任意一个数字就需要删除此整个字符串)

import java.util.ArrayList;
public class test02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String s1 = "ab1";
        String s2 = "123ad";
        String s3 = "bca";
        String s4 = "dadfadf";
        String s5 = "dddaaa";
        String s6 = "你好呀";
        String s7 = "我来啦";
        String s8 = "别跑啊";

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
        list.add(s8);

        for (int i = 0; i < list.size(); i++) {

            String s = list.get(i);
            int length = s.length();
            if (length>5){
                list.remove(s);
                i--;
            }
        }

        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            for (int j = 0; j < s.length(); j++) {
               char c = s.charAt(j);
               if (c>='0'&&c<='9'){
                   list.remove(s);
                   i--;
                   break;
               }
            }
        }
        System.out.println(list);
    }
}
