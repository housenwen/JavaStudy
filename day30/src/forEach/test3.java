package forEach;

import java.util.ArrayList;
import java.util.List;

public class test3 {

    public static void main(String args[]) {
        List names = new ArrayList();
        names.add("大明");
        names.add("二明");
        names.add("小明");
        //得到集合的stream并循环引用输出语句
        names.stream().forEach(System.out::println);
        System.out.println("---------------------------");
        names.forEach(s-> System.out.println(s));
    }
}
