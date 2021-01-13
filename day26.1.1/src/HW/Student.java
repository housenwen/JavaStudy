package HW;

import java.io.Serializable;

//	请按以下要求编写程序：
//
//		1).定义一个学员类，有以下属性：姓名、性别、年龄、分数
//
//		  无参、全参构造方法，get/set方法
public class Student implements Serializable {
//    private static final long serialVersionUID = 1L;
    public String name;
    public String sex;
    public int age;
    public transient int score;

    public Student() {
    }

    public Student(String name, String sex, int age, int score) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
