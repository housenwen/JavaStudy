package CW;

public class demo4 {
    public static void main(String[] args) {
        /**
         * 4.5 数量词
         *
         * - 语法示例：
         *   1. X? : 0次或1次
         *   2. X* : 0次到多次
         *   3. X+ : 1次或多次
         *   4. X{n} : 恰好n次
         *   5. X{n,} : 至少n次
         *   6. X{n,m}: n到m次(n和m都是包含的)
         */

        String str = "";

        //1.验证str是否是三位数字
        str = "012";
        String regex = "\\d{3}";
        System.out.println("1."+str.matches(regex));

        //2.验证str是否是多位数字
        str = "45678934567";
        regex = "\\d+";
        System.out.println("2."+str.matches(regex));

        //3.验证str是否是手机号：
        str = "12345678999";
        regex = "1[358]\\d{9}";
        System.out.println("3."+str.matches(regex));

        //4.验证小数:必须出现小数点，但是只能出现1次
        str = "3.1";
        regex = "\\d*\\.{1}\\d+";
        System.out.println("4."+str.matches(regex));

        //5.验证小数：小数点可以不出现，也可以出现1次
        regex = "\\d+\\.?\\d+";
        System.out.println("5."+str.matches(regex));

        //6.验证小数：要求匹配：3、3.、3.14、+3.14、-3.
        str = "-3.";
        regex = "[+-]\\d+\\.?\\d*";
        System.out.println("6." + str.matches(regex));

        //7.验证qq号码：1).5--15位；2).全部是数字;3).第一位不是0
        str = "124567456";
        regex = "[1-9]\\d{4,14}";
        System.out.println("7." + str.matches(regex));



    }
}
