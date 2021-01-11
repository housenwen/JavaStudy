package forEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class work1 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("张三丰", "王思聪", "张飞", "刘晓敏", "张靓颖"));

//        list.stream().forEach(s -> {
//            System.out.println(s);
//        });

        Stream<String> stream = list.stream();

        list.stream().forEach(s -> {
            System.out.println(s);
        });

        System.out.println("---------------------");
//        list.stream().forEach(s -> s.startsWith("张"));

//        list.stream().filter(s->s.startsWith("张")).forEach(s->{
//            System.out.println(s);
//        });
        System.out.println("----------------------");
        stream.forEach(s-> System.out.println(s));
        System.out.println("--------------");
        list.stream().filter(s->s.startsWith("张")).forEach(s ->
                System.out.println(s)
        );

       long c =  list.stream().filter(s -> s.startsWith("张")).count();

        System.out.println(c);


    }
}
