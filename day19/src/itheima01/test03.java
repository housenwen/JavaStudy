package itheima01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//输入一个字符串中每个字符出现次数。
public class test03 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true){
        System.out.println("请输入一个字符串：");
        String str = sc.next();

        Integer integer = str.length();

        Map<String, Integer> map = new HashMap<>();

        map.put(str,integer);

        int index =  getCount(map,str);

        System.out.println("字符串字符出现的次数："+index);

    }}

    private static int getCount(Map<String, Integer> map, String str) {

        int index = -1;

        Set<Map.Entry<String,Integer>> set = map.entrySet();

        for (Map.Entry<String,Integer> entry :set){

            if (str.equals(entry.getKey())){

                index = entry.getValue();
            }
            System.out.println(entry);

        }
        return index;

    }
}
