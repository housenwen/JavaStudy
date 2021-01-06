package HomeWork;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
*1.利用键盘录入，输入一个字符串,统计该字符串中各个字符的数量

*2.如用户输入字符串:"helloworld java",
 *
 * 程序输出结果：h(1)e(1)l(3)o(2) (2)w(1)r(1)d(1)j(1)a(2)v(1)

*3.注：输出结果不要求顺序一致

        */
public class test08 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一串字符串");
        String str= sc.nextLine();

        Map<Character,Integer> map = new LinkedHashMap<>();

        char[] chars = str.toCharArray();

        for (char c :chars){

            if (map.containsKey(c)){
                //先获取之前的次数
                //再次存入  更新
                Integer count = map.get(c);
                map.put(c,++count);
            }else {

                map.put(c,1);
            }

        }

        System.out.println(map);

        Set<Character> set = map.keySet();

        for (Character character:set){
            System.out.print(character+"("+map.get(character)+")");
        }

    }
}
