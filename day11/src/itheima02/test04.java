package itheima02;
/*
4.1． 训练描述： 分析以下需求,并用代码实现 1.键盘录入一个字符串
2.将该字符串变成字符数组(不能使用 toCharArray()方法)
3.将字符数组中的所有大写字母变成小写字母(不能使用 toLowerCase()方法)
4.如果第一位和最后一位的内容不相同,则交换
5.将字符数组中索引为偶数的元素变成'~'
6.打印数组元素的内容
        */
public class test04 {

    public static void main(String[] args) {
        String  s = "woaijava,woailiming,WOAILIUDEHUA";
        char[] chars = new char[s.length()] ;

        for (int i = 0; i < s.length(); i++) {

            chars[i] = s.charAt(i);

        }

        System.out.println(chars);

        char[] chars1 = s.toCharArray();
        System.out.println(chars1);
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i]>='A'&&chars[i]<='Z'){
                chars[i]+=32;
            }

//            if (i%2==0){
//
//                chars[i] = '~';

//            }
        }
        System.out.println(chars);

        for (int i = 0; i < chars.length; i++) {
            if (chars[0]!=chars[chars.length-1]){
                char cc = chars[0];
                chars[0]= chars[chars.length-1];
                chars[chars.length-1]=cc;
                System.out.println(chars);
                break;
            }
        }


    }

}
