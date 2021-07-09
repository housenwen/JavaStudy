package com.heima.stream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/4/15 21:43
 **/
public class StreamTest {

    public static void main(String[] args) {
// 集合类 . stream 获取流对象
        List<Student> studentList = getStudentList();
//        Map<String, Double> collect = studentList.stream()
//                .filter((stu) -> stu.getAge() > 30)
//                .distinct()
////                .skip(2)  // student  Map  key: 名称   value: sarlary
////                .limit(2)
////                .map(Student::getSalary).sorted(Comparator.comparing(Student::getAge).reversed())
////                .forEach((stu)->System.out.println(stu));// 终止操作
////                .forEach(System.out::println);// 终止操作
//                .collect(Collectors.toMap(Student::getName, Student::getSalary));// 终止操作

//        String collect = studentList.stream()
//                .filter((stu) -> stu.getAge() > 30)
//                .distinct()
//                .map(Student::getName)
////                .skip(2)  // student  Map  key: 名称   value: sarlary
////                .limit(2)
////                .map(Student::getSalary).sorted(Comparator.comparing(Student::getAge).reversed())
////                .forEach((stu)->System.out.println(stu));// 终止操作
////                .forEach(System.out::println);// 终止操作
//                .collect(Collectors.joining(","));

        Map<String, List<Student>> collect = studentList.stream()
                .filter((stu) -> stu.getAge() > 30)
                .distinct()
//                .map(Student::getName)
//                .skip(2)  // student  Map  key: 名称   value: sarlary
//                .limit(2)
//                .map(Student::getSalary).sorted(Comparator.comparing(Student::getAge).reversed())
//                .forEach((stu)->System.out.println(stu));// 终止操作
//                .forEach(System.out::println);// 终止操作
                .collect(Collectors.groupingBy(Student::getSex));
        System.out.println(collect);
    }
    public static List<Student> getStudentList(){
        return Arrays.asList(
                new Student(1,"赵云",28,"男",18888.0),
                new Student(2,"孙尚香",22,"女",23145.0),
                new Student(3,"关羽",31,"男",21321.0),
                new Student(4,"貂蝉",44,"女",9000.0),
                new Student(5,"刘备",51,"男",54000.0),
                new Student(6,"甄姬",25,"女",16888.0),
                new Student(7,"曹操",66,"男",17888.0),
                new Student(8,"小乔",34,"女",28888.0),
                new Student(8,"小乔",34,"女",28888.0)
        );
    }
    static class Student{
        private Integer id;
        private String name;
        private Integer age;
        private String sex;
        private Double salary;
        public Student(Integer id, String name, Integer age, String sex, Double salary) {
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

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }
    }
}
