package CW;

public class test {
    public static void main(String[] args) {
        //将下面字符串中的"数字"替换为"*"
        String str = "jfdk432jfdk2jk24354j47jk5l31324";

        System.out.println(str.replaceAll("\\d+", "*"));
        System.out.println(str.replaceAll("\\d*", "*"));
        System.out.println(str.replaceAll("\\d?", "*"));
        System.out.println(str.replaceAll("\\d{1}", "*"));
        System.out.println(str.replaceAll("\\d{1,}", "*"));
        System.out.println(str.replaceAll("\\d{1,4}", "*"));

    }
}