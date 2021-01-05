package itheima05;

import java.util.ArrayList;
import java.util.Collections;
//需求：定义一个方法int  listTest(ArrayList<Integer> al, Integer s)，
// 要求返回s在al里面第一次出现的索引，如果s没出现过返回-1。
public class test02 {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        Collections.addAll(al,2,39,4,2,3,0,9);
        Integer s = new Integer(8);

        int i =  listTest(al, s);

        System.out.println(i);
    }

    private static int listTest(ArrayList<Integer> al, Integer s) {
        int index = -1;
        for (int i = 0; i < al.size(); i++) {

            if (s.equals(al.get(i))){
                index=i;
            }

        }
        return index;
    }
}
