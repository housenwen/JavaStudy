package Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1
 * 《教父》
 * 《霸王别姬》
 * 2
 * 《肖申克的救赎》
 * 《大闹天宫》
 * 3
 * 《辛德勒的名单》
 * 《鬼子来了》
 * 4
 * 《公民凯恩》
 * 《大话西游》
 * 5
 * 《卡萨布兰卡》
 * 《活着》
 * 6
 * 《教父续集》
 * 《饮食男女》
 * 7
 * 《七武士》
 * 《无间道》
 *8
 * 《星球大战》
 * 《天书奇谭》
 * 9
 * 《美国美人》
 * 《哪吒脑海》
 * 10
 * 《飞跃疯人院》
 * 《春光乍泄》
 */
public class test5 {
    public static void main(String[] args) {

        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();

        Collections.addAll(one,"教父","申肖克的救赎","辛德勒的名单",
                "公民凯恩","卡萨布兰卡",
                "教父续集","七武士","星球大战","美国美人","飞跃疯人院");

        Collections.addAll(two,"霸王别姬","大闹天宫","鬼子来了","大话西游","活着",
                "饮食男女","无间道","天书奇谭","哪吒闹海","春光乍泄");

        one.stream().limit(3).forEach(s -> System.out.println(s));
        System.out.println("--------------------");
        two.stream().skip(5).forEach(s -> System.out.println(s));
        System.out.println("-----------");
        Stream<String> s1 = one.stream().limit(5);
        Stream<String> s2 = two.stream().limit(5);

        List<String> list = Stream.concat(s1,s2).collect(Collectors.toList());

        System.out.println(list);

//      list.stream().map(Film::new).forEach(film -> System.out.println(film));

        List<Film> films = Stream.concat(one.stream(),two.stream())
                .map(Film::new).collect(Collectors.toList());

        films.stream().forEach(System.out::println);


    }
}
