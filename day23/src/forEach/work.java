package forEach;

import java.util.ArrayList;
import java.util.Arrays;

public class work {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(
                Arrays.asList("王佳乐", "张三丰", "王思聪", "张飞", "刘晓敏", "张靓颖", "王敏"));
//"张姓学员，获取前2个"
        list.stream().filter(s -> s.startsWith("张")).limit(2).forEach(s -> System.out.println(s));
        System.out.println("------------------");
//        System.out.println("王姓学员，跳过前1个");
        list.stream().filter(s -> s.startsWith("王")).skip(1).forEach(s-> System.out.println(s));

    }
}
