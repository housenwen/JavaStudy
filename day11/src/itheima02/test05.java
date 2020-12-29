package itheima02;

import java.sql.Array;
import java.util.Arrays;
import java.util.Random;

/*5.1． 训练描述： 分析以下需求,并用代码实现
1.定义 String getStr(char[] chs)方法
功能描述：获取长度为 5 的随机字符串，字符串由随机的 4 个大写英文字母和 1 个 0-9 之间（包含 0 和 9）的整数组成
 2.定义 main 方法，方法内完成：
 (1)定义长度为 26，元素值为 26 个大写英文字母的数组 chs
 (2)传递数组 chs 调用 getStr(char[] chs)方法，获取返回值,并在控制台打印返回值
*

* */
public class test05 {
    public static void main(String[] args) {

        char [] chs = new char[5];

        getStr(chs);
        System.out.println(Arrays.toString(chs));

        char[] chars = new char[26];
        getStr(chars);
        System.out.println(Arrays.toString(chars));

    }

    private static String  getStr(char[] chs) {
             Random random = new Random();
        for (int i = 0; i < chs.length; i++) {

            if (i==chs.length-1){
                int k = random.nextInt(57-49+1)+49;
                chs[i]=(char) k;
                System.out.println(chs[i]);
            }
            else {
                int s = random.nextInt(90-65+1)+65;
                chs[i] = (char) s;
                System.out.println(chs[i]);
            }

        }
        return "";
    }
}
