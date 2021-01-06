package itheima01;

import java.util.LinkedHashMap;
import java.util.Set;

public class test01 {
    public static void main(String[] args) {
//        创建LinkedHashMa对象
        LinkedHashMap<Student,String> map = new LinkedHashMap<>();

        map.put(new Student("张三",28),"北京");
        map.put(new Student("李四",28),"河北");
        map.put(new Student("王五",28),"北京");
        map.put(new Student("王五",28),"上海");

//        Set<Student> keySet = map.keySet();
//
//        for (Student key:keySet){
//
//            String value = map.get(key);
//
//            System.out.println(key.toString()+"..."+value);
//
//        }

//        Set<Student>  set = map.keySet();
//
//        for (Student s :set){
//
//            String str = map.get(s);
//
//            System.out.println(s.toString()+"...."+str);
//        }

        Set<Student> set = map.keySet();

        for (Student key :set){

            String value = map.get(key);

            System.out.println(key.toString()+"...."+value);

        }

    }
}
