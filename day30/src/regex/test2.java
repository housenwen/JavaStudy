package regex;

public class test2 {
    public static void main(String[] args) {
        String str = "ead";

        //1.要求字符串是否是除a、e、i、o、u外的其它小写字符开头，后跟ad
        String regex = "[a-z&&[^aeiou]]ad";
        System.out.println("1." + str.matches(regex));

        //2.要求字符串是aeiou中的某个字符开头，后跟ad
        regex = "[a|e|i|o|u]ad";//这种写法相当于：regex = "[aeiou]ad";
        System.out.println("2." + str.matches(regex));
    }
}
