package forEach;

import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String args[]) {
        List names = new ArrayList();

        names.add("大明");
        names.add("二明");
        names.add("小明");

        names.forEach(System.out::println);
    }
}
