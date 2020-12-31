package itheima;

public class test02 {
    public static void main(String[] args) {
//        需求：键盘接受一个字符串，程序判断出该字符串是否是对称字符串，并在控制台打印是或不是
//        对称字符串：123321、111
//        非对称字符串：123123

        String str = "1223321";

        StringBuilder sb = new StringBuilder(str);

//        sb.reverse();

        if (str.equals(sb.reverse().toString())){
            System.out.println("对称字符串");
        }else {
            System.out.println("非对称字符串");
        }



    }
}
