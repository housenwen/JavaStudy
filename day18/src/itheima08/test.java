package itheima08;
//需求：创建一个类，提供一个功能，向LinkedList集合中添加自定义的Person类对象；要求：
//
//1. Person应该具有姓名、性别和年龄三个属性；
//2. 添加对象时，如果容器中已存在该对象，就不添加；（要求：不能使用集合提供的contains方法）
//3. 如果Person对象的姓名和性别相同，就认为是同一个人；

import sun.plugin.javascript.navig.Link;

import java.util.ArrayList;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) {

        LinkedList<Person> list = new LinkedList<>();

        Person p = new Person("张飞","20","男");
        Person p2 = new Person("刘备","26","男");
        Person p3 = new Person("关羽","22","男");
        Person p4 = new Person("关羽","23","男");

//        Collections.addAll(list,p1,p2,p3,p4);
//        System.out.println(list);
        addPerson(list,p);
        addPerson(list,p2);
        addPerson(list,p3);
        addPerson(list,p4);

        for (Person person:list){
            System.out.println(person);
        }
        list.add(p2);
        System.out.println(list);
    }

    private static void addPerson(LinkedList<Person> list, Person p) {

       for (Person person:list){
           if (p.equals(person)){
               return;
           }
       }
          list.add(p);
    }
}
