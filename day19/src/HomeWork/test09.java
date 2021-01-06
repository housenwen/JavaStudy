package HomeWork;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
   键盘录入一个字符串,分别统计出其中英文字母、空格、数字和其它字符的数量,
   输出结果:”其他=1, 空格=2, 字母=12, 数字=6”
 */
public class test09 {
    public static void main(String[] args) {

        Map<String,Integer> map = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String str = sc.nextLine();

        char[] chars = str.toCharArray();

        for (char c :chars) {
            if (c>='a'&&c<='z'||c>='A'&&c<='Z'){
                    countingKey(map, "字母");
            }else if (c==' '){
                countingKey(map,"空格");
            }else if (c>='0'&&c<='9'){

                countingKey(map,"数字");
            }else {
                countingKey(map,"其他");
            }

            }

        System.out.println(map);
            
        }


    // 1.定义countingKey(HashMap<String, Integer> map, String key)方法.
    // 用于统计key出现的次数.调用一次,让key所对应的次数+1.
    private static void countingKey(Map<String, Integer> map, String key) {
        // 2.在countingKey方法中.使用get方法从map中取出key的值
        Integer num = map.get(key);
        // 3.如果为空,说明key第一次出现
        if (num==null){
            // 4.放入key,把次数设置为1
            map.put(key,1);
        }else {
            // 5.如果之前,已经有key了,就把key的次数+1
            map.put(key,num+1);
        }
    }
}
