package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 现在有两个 ArrayList 集合存储队伍当中的多个成员姓名，要求使用Stream方式进行以
 * 下若干操作步骤：
 * 1. 第一个队伍只要名字为3个字的成员姓名；
 * 2. 第一个队伍筛选之后只要前6个人；
 * 3. 第二个队伍只要姓张的成员姓名；
 * 4. 第二个队伍筛选之后不要前1个人；
 * 5. 将两个队伍合并为一个队伍；
 * 6. 根据姓名创建Student对象；
 * 7. 打印整个队伍的Student对象信息。
 */
public class test4 {
    public static void main(String[] args) {
        List<String> one = new ArrayList<>();
        one.add("陈玄风");
        one.add("梅超风");
        one.add("陆乘风");
        one.add("曲灵风");
        one.add("武眠风");
        one.add("冯默风");
        one.add("罗玉风");
        List<String> two = new ArrayList<>();
        two.add("宋远桥");
        two.add("俞莲舟");
        two.add("俞岱岩");
        two.add("张松溪");
        two.add("张翠山");
        two.add("殷梨亭");
        two.add("莫声谷");

        one.stream().filter(s -> s.length()==3).limit(6).forEach(s -> System.out.println(s));
        System.out.println("----------------");
        two.stream().filter(s -> s.contains("张")).skip(1).forEach(s -> System.out.println(s));

        System.out.println("------------------");

        Stream<String> s1 = one.stream().filter(s -> s.length()==3).limit(6);

        Stream<String> s2 = two.stream().filter(s -> s.contains("张")).skip(1);

//        List<String> list =  Stream.concat(s1,s2).collect(Collectors.toList());

//        System.out.println(list);

//        Stream.concat(s1,s2).map(Student::new).forEach(System.out::println);
        Stream.concat(s1,s2).map(Student::new).forEach(student -> System.out.println(student));

    }
}
