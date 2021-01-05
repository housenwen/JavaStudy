package Sex;

import java.util.Collections;
import java.util.HashSet;

//需求：使用HashSet集合，保存自定义的Person类对象；要求：
//
//1. Person应该具有姓名、性别和年龄三个属性；
//2. 如果Person对象的姓名和性别相同，就认为是同一个人；
public class test {
    public static void main(String[] args) {
        Person p1 = new Person("周杰伦","男",20);
        Person p2 = new Person("林俊杰","男",22);
        Person p3 = new Person("蔡依林","女",21);
        Person p4 = new Person("蔡依林","女",23);

        HashSet<Person> set = new HashSet<Person>();
        Collections.addAll(set,p1,p2,p3,p4);

        for (Person person:set){
            System.out.println(person);
        }

    }
}
