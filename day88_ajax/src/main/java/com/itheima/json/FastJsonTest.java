package com.itheima.json;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Cat;
import com.itheima.pojo.Dog;
import com.itheima.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    public static void main(String[] args) {

        Cat cat = new Cat();
        cat.setName("小菊");
        cat.setType("橘猫");
        //单个java对象转换成json
        String catJson = JSON.toJSONString(cat);
        System.out.println(catJson);


        List<Dog> dogs = new ArrayList<>();
        Dog dog1 = new Dog();
        dog1.setName("短腿");
        dog1.setType("柯基");

        Dog dog2 = new Dog();
        dog2.setName("日天");
        dog2.setType("泰迪");

        dogs.add(dog1);
        dogs.add(dog2);

        //集合转换成json数组格式
        String dogsJsonStr = JSON.toJSONString(dogs);
        System.out.println(dogsJsonStr);


        //复杂类型
        Person person = new Person();
        person.setUsername("张三丰");
        person.setPassword("123");
        person.setCat(cat);
        person.setDogs(dogs);
        String personJsonStr = JSON.toJSONString(person);
        System.out.println(personJsonStr);

        //将字符串反转换成java中的对象
        Cat cat1 = JSON.parseObject(catJson, Cat.class);
        System.out.println(cat1.getName());

    }
}
