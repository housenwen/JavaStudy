package forEach;

/**
 * 编写程序，请用户输入一个“QQ号码”，定义正则表达式，验证这个QQ号码是否符合以下要求：
 *
 *     \1. 5--12位长度
 *
 *     \2. 全部是数字
 *
 *     \3. 首位不能是0
 */
public class test {
    public static void main(String[] args) {
        String str = "23456";
        String regex = "[1-9]\\d{4,11}";
        System.out.println(str.matches(regex));
    }

}
