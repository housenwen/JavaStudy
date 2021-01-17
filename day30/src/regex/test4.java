package regex;

import java.util.Arrays;

public class test4 {
    public static void main(String[] args) {
        String str = "18  4 567       99     56";
        String[] strArray = str.split(" +");
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(strArray[i]);
        }
        System.out.println(Arrays.toString(strArray));
    }
}
