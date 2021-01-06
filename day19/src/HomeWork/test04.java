package HomeWork;

import java.util.*;

// ArrayList有以下元素: "a","f","b","c","a","d"，
// 请用任意方式完成集合元素去重。去除集合中重复的元素。
public class test04 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"a","f","b","c","a","d");

        System.out.println(list);

        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.addAll(list);
        System.out.println(set);

    }
}
