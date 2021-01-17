package forEach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeSet;

public class test9 {
    public static void main(String[] args) {
       String str =  "我我....我...我.要...要要...要学....学学..学.编..编编.编.程.程.程..程";
        ArrayList<Character> list = new ArrayList<>();

        TreeSet<Character> set = new TreeSet<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char s = chars[i];
            set.add(s);
            list.add(s);
        }
        set.remove(".".charAt(0));
        System.out.println(set);
        System.out.println(list);

    }
}
