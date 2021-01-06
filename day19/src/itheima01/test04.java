package itheima01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
//输入一个字符串中每个字符出现次数。的Map集合。
public class test04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入一个字符串：");
            String line = sc.next();
            findChar(line);
        }
    }
    private static void findChar(String line) {
        Map<Character,Integer> map = new HashMap<>();

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (!map.containsKey(c)){
                map.put(c,1);
            }else {

                Integer count = map.get(c);
                map.put(c,++count);
            }

        }

        Set<Map.Entry<Character,Integer>> set = map.entrySet();

        for (Map.Entry<Character,Integer> entry:set){
            System.out.println(entry);
        }

    }
}
