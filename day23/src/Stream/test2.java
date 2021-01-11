package Stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test2 {
    public static void main(String[] args) {

        Stream<String> streamA = Stream.of("黄蓉","穆念慈");
        Stream<String> streamB = Stream.of("郭靖","杨康");
        List<String> list = Stream.concat(streamA,streamB).collect(Collectors.toList());
        System.out.println(list);
    }
}
