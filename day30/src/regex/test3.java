package regex;

public class test3 {
    public static void main(String[] args) {
        String str = "DG8FV-B9TKY-FRT9J-99899-XPQ4G";

        //验证这个序列号：分为5组，每组之间使用-隔开，每组由5位A-Z或者0-9的字符组成
        String regex = "([A-Z0-9]{5}-){4}[A-Z0-9]{5}";
        System.out.println(str.matches(regex));

    }
}
