package com.itheima.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Cat;
import com.itheima.pojo.Dog;
import com.itheima.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class JacksonTest {

    public static void main(String[] args) throws Exception {
        //创建核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        Cat cat = new Cat();
        cat.setName("小菊");
        cat.setType("橘猫");
        //单个对象
        String s = objectMapper.writeValueAsString(cat);
        System.out.println(s);

        List<Dog> dogs = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setName("短腿");
        dog1.setType("柯基");

        Dog dog2 = new Dog();
        dog2.setName("日天");
        dog2.setType("泰迪");

        dogs.add(dog1);
        dogs.add(dog2);
        //集合类型
        String s1 = objectMapper.writeValueAsString(dogs);
        System.out.println(s1);


        Person person = new Person();
        person.setUsername("张三丰");
        person.setPassword("123");
        person.setCat(cat);
        person.setDogs(dogs);
        //复杂类型
        String s2 = objectMapper.writeValueAsString(person);
        System.out.println(s2);

        //json字符串转换成java对象
        Cat cat1 = objectMapper.readValue(s, Cat.class);
        System.out.println(cat1.getName());
    }
}
