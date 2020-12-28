package itheima06;

import java.util.ArrayList;
import java.util.List;
//删除集合中元素包含0-9数字的字符串(只要字符串中包含0-9中的任意一个数字就需要删除此整个字符串)
public class test03 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("fhds");
        list.add("sdhj");
        list.add("gdja6");
        list.add("dhhdw");
        list.add("fh4ds");
        list.add("528947141");
        list.add("had");
        System.out.println(list);
        getList(list);
        System.out.println(list);

    }

    private static void getList(ArrayList<String> list) {

        for (int i = 0; i < list.size(); i++) {
            char[] s1 = list.get(i).toCharArray();
            for (int j = 0; j < s1.length; j++) {
                if(s1[j] >='0' && s1[j] <= '9') {
                    list.remove(i);
                    i--;
                    break;
                }
            }
        }

    }

}
