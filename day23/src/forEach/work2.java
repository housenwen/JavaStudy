package forEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class work2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(
                Arrays.asList("王佳乐", "张三丰", "王思聪", "张飞", "刘晓敏", "张靓颖", "王敏"));

        Stream<String> list1 = Stream.concat(
                list.stream().filter(s -> s.startsWith("王")),
                list.stream().filter(s -> s.startsWith("张"))
        );

        list1.forEach(s -> System.out.println(s));
        System.out.println("-------------------");
        Stream.concat(
                list.stream().filter(s -> s.startsWith("王")),
                list.stream().filter(s -> s.startsWith("张"))
        ).forEach(s -> System.out.println(s));
    }
}
