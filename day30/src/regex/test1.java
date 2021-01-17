package regex;

public class test1 {
    public static void main(String[] args) {
        String str = "258";

        //1.验证str是否3位数字
        String regex = "\\d\\d\\d";
        System.out.println("1." + str.matches(regex));

        //2.验证手机号：1开头，第二位：3/5/8，剩下9位都是0-9的数字
        str = "13513153355";//要验证的字符串
        regex = "1[358]\\d\\d\\d\\d\\d\\d\\d\\d\\d";//正则表达式
        System.out.println("2." + str.matches(regex));

        //3.验证字符串是否以h开头，以d结尾，中间是任何字符
        str = "had";//要验证的字符串
        regex = "h.d";//正则表达式
        System.out.println("3." + str.matches(regex));

        //4.验证str是否是：had.
        str = "had.";//要验证的字符串
        regex = "had\\.";//\\.代表'.'符号，因为.在正则中被预定义为"任意字符"，不能直接使用
        System.out.println("4." + str.matches(regex));
    }
}
