package Test;
//输入一个字符串中每个字符出现次数。
//
//分析：
//
//1. 获取一个字符串对象
//2. 创建一个Map集合，键代表字符，值代表次数。
//3. 遍历字符串得到每个字符。
//4. 判断Map中是否有该键。
//5. 如果没有，第一次出现，存储次数为1；如果有，则说明已经出现过，获取到对应的值进行++，再次存储。
//6. 打印最终结果
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Character,Integer> map = new LinkedHashMap<>();
        System.out.println("请输入：");
        String line = sc.nextLine();

        char[] chars = line.toCharArray();

        for (char c :chars){
            if (map.containsKey(c)){
                Integer num = map.get(c);
                map.put(c,num+1);
            }else {
                map.put(c,1);
            }
        }

        System.out.println(map);
    }
}
