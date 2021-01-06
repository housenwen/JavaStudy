package itheima01;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
        TreeMap<Student,String> map = new TreeMap<Student,String>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int result = o1.getAge()-o2.getAge();
                if (result==0){
                    return o1.getName().charAt(0)-o2.getName().charAt(0);
                }else {
                    return result;
                }

            }
        });

        map.put(new Student("aack",10 ), "深圳");
        map.put(new Student("zose",10 ), "北京");
        map.put(new Student("tom",20 ), "上海");
        map.put(new Student("marry",30 ), "南京");
        map.put(new Student("lucy",30 ), "广州");
        map.put(new Student("lucy",30 ), "广州");

        Set<Map.Entry<Student,String>> entrySet = map.entrySet();
        for (Map.Entry<Student, String> entry:entrySet){
            System.out.println(entry);
        }
    }
}
