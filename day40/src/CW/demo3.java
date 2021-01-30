package CW;

/**4.4 预定义字符
 * 1. "." ： 匹配任何字符。
 * 2. "\d"：任何数字[0-9]的简写；
 * 3. "\D"：任何非数字[^0-9]的简写；
 * 4. "\s"： 空白字符：[ \t\n\x0B\f\r] 的简写
 * 5. "\S"： 非空白字符：[^\s] 的简写
 * 6. "\w"：单词字符：[a-zA-Z_0-9]的简写
 * 7. "\W"：非单词字符：[^\w]
 */
public class demo3 {
    public static void main(String[] args) {
        String str = "258";

        //1.验证str是否3位数字
        String regex = "\\d\\d\\d";
        System.out.println(str.matches(regex));

        //2.验证手机号：1开头，第二位：3/5/8，剩下9位都是0-9的数字
        str = "13513153355";//要验证的字符串
        regex = "1[358]\\d\\d\\d\\d\\d\\d\\d\\d\\d";
        System.out.println(str.matches(regex));

        //3.验证字符串是否以h开头，以d结尾，中间是任何字符
        str = "had";
        regex = "h.d";
        System.out.println(str.matches(regex));

        //4.验证str是否是：had.
        str = "had.";
        regex = "had\\.";//todo \\.代表'.'符号，因为.在正则中被预定义为"任意字符"，不能直接使用
        System.out.println(str.matches(regex));
    }
}
