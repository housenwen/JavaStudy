package forEach;

public class test4 {
//    编写程序，请用户输入一个“手机号码”，定义正则表达式，验证这个手机号码是否符合以下要求：
//
//            \1. 必须以数字1开头；
//
//            \2. 第二位是：3,5,7,8中的一位
//
//    \3. 剩下的全部是数字

    public static void main(String[] args) {

        String mobile = "13482530510";
        String regex = "1[3578]\\d{9}";
        System.out.println(mobile.matches(regex));

    }

}
