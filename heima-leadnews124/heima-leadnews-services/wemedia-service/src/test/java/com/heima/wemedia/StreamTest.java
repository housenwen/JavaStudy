package com.heima.wemedia;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/5/26 15:35
 **/
public class StreamTest {

    @Test
    public void createStream(){
        //
        list.stream()
                .distinct()
//                .skip(2)
//                .limit(5)
                .filter((student) -> student.getSalary() > 40000)
//                .flatMap((student -> {
//                    Integer[] arr = {student.getAge(),student.getSalary()};
//                    return Arrays.stream(arr);
//                }))
                .sorted(Comparator.comparing(Student::getSalary).reversed())
//                .map(student -> {
//                    Map map = new HashMap<>();
//                    map.put("name", student.getName());
//                    map.put("salary", student.getSalary());
//                    return map;
//                })
//                .map(Student::getSalary)
                .collect(Collectors.toMap(Student::getName, Student::getSalary));
//        collect.forEach((key,val)->{
//            System.out.println(key + "   ====   "+ val);
//        });
//        StringBuilder stringBuilder = new StringBuilder("【");
//        for (Student student : list) {
//            if("女".equals(student.getSex())){
//                stringBuilder.append(student.getName() + ",");
//            }
//        }
//        stringBuilder.append("】");
        String collect = list.stream()
                .filter(student -> "女".equals(student.getSex()))
                .map(Student::getName)
                .collect(Collectors.joining(",", "【", "】"));

        System.out.println(collect);
    }


    List<Student> list = Arrays.asList(
            new Student(1,"关羽",53,"男",30000),
            new Student(2,"张飞",48,"男",25000),
            new Student(2,"张飞",48,"男",25000),
            new Student(2,"张飞",48,"男",25000),
            new Student(3,"赵云",34,"男",23000),
            new Student(4,"马超",32,"男",18000),
            new Student(5,"刘备",59,"男",50000),
            new Student(6,"曹操",63,"男",70000),
            new Student(7,"大乔",25,"女",40000),
            new Student(8,"小乔",23,"女",40000),
            new Student(9,"甄姬",31,"女",25000),
            new Student(10,"孙尚香",27,"女",34900)
    );
    class Student{
        private Integer id;
        private String name;
        private Integer age;
        private String sex;
        private Integer salary;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return Objects.equals(id, student.id) &&
                    Objects.equals(name, student.name) &&
                    Objects.equals(age, student.age) &&
                    Objects.equals(sex, student.sex) &&
                    Objects.equals(salary, student.salary);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, age, sex, salary);
        }

        public Student() {
        }

        public Student(Integer id, String name, Integer age, String sex, Integer salary) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }
    }
}
