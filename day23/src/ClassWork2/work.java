package ClassWork2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class work {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        Collections.addAll(list,"王佳乐", "张三丰", "王思聪", "张飞");

        List<Person> personList = list.stream().map(s -> new Person(s))
                .collect(Collectors.toList());

        for (Person p :personList){
            System.out.println(p.getName());
        }
        System.out.println("-------------------");
        personList.stream().forEach(person -> System.out.println(person.getName()));

    }
}
