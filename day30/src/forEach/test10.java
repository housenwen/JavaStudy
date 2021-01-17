package forEach;

import java.util.Arrays;

public class test10 {
    public static void main(String[] args) {
        String data = "我我....我...我.要...要要...要学....学学..学.编..编编.编.程.程.程..程";

        String regex = "\\.+";
        String newStr = data.replaceAll(regex, "");
        System.out.println(newStr);
        String regex2 = "(.)\\1+";
        String result = newStr.replaceAll(regex2, "$1");
        System.out.println(result);

    }
}
