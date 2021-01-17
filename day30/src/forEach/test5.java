package forEach;

public class test5 {
    public static void main(String[] args) {
        //定义变量保存字符串
        String str = "斯蒂芬斯的粉丝";
        //定义变量保存正则表达式字符串
        //todo 匹配中文字符的正则：[\u4e00-\u9fa5];
        String regex = "[\\u4e00-\\u9fa5]";
        boolean flag = str.matches(regex);
        System.out.println(flag);
    }
}
