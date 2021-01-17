package regex;

public class test {
    public static void main(String[] args) {
        String str = "";

        //1.验证str是否是三位数字
        str = "012";
        String regex = "\\d{3}";
        System.out.println("1." + str.matches(regex));

        //2.验证str是否是多位数字
        str = "88932054782342";
        regex = "\\d+";
        System.out.println("2." + str.matches(regex));

        //3.验证str是否是手机号：
        str = "13813183388";
        regex = "1[358]\\d{9}";
        System.out.println("3." + str.matches(regex));

        //4.验证小数:必须出现小数点，但是只能出现1次
        String s2 = "3.1";
        regex = "\\d*\\.{1}\\d+";
        System.out.println("4." + s2.matches(regex));

        //5.验证小数：小数点可以不出现，也可以出现1次
        regex = "\\d+\\.?\\d+";
        System.out.println("5." + s2.matches(regex));

        //6.验证小数：要求匹配：3、3.、3.14、+3.14、-3.
        s2 = "-3.";
        regex = "[+-]\\d+\\.?\\d*";
        System.out.println("6." + s2.matches(regex));

        //7.验证qq号码：1).5--15位；2).全部是数字;3).第一位不是0
        s2 = "1695827736";
        regex = "[1-9]\\d{4,14}";
        System.out.println("7." + s2.matches(regex));
    }
    }

