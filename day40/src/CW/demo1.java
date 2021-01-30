package CW;

public class demo1 {
    public static void main(String[] args) {
        String str = "ead";

        //1.验证str是否以h开头，以d结尾，中间是a,e,i,o,u中某个字符
        String regex = "e[aeiou]d";
        System.out.println("1."+str.matches(regex));

        //2.验证str是否以h开头，以d结尾，中间不是a,e,i,o,u中的某个字符
        String regex2 = "e[^ferty]d";
        System.out.println("2."+str.matches(regex2));

        //3.验证str是否a-z的任何一个小写字符开头，后跟ad
        String regex3 = "[a-z]ad";
        System.out.println("3."+str.matches(regex3));

        //4.验证str是否以a-d或者m-p之间某个字符开头，后跟ad
        String regex4 = "[[a-d][m-p]]ad";
        System.out.println("4."+str.matches(regex4));

    }
}
