package itheima;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 键盘录入一个字符串,分别统计出其中英文字母、空格、数字和其它字符的数量,
 输出结果:”其他=1, 空格=2, 字母=12, 数字=6”
 */
public class test03 {
    public static void main(String[] args) {

        Map<String,Integer> map = new LinkedHashMap<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入字符串：");
        String str = sc.nextLine();

        char[] chars = str.toCharArray();

        for (char c :chars){
            if (c>='a'&&c<='z'||c>='A'&&c<='Z'){
                countKey(map,"字母");

            }else if (c==' '){
                countKey(map,"空格");
            }else if (c>='0'&&c<='9'){
                countKey(map,"数字");

            }else {

                countKey(map,"其他");
            }
        }

        System.out.println(map);

    }

    private static void countKey(Map<String, Integer> map, String key) {
        Integer num = map.get(key);
        if (num ==null){

            map.put(key,1);

        }else {

            map.put(key,++num);

        }

    }
}
