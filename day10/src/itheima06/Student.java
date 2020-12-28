package itheima06;
//3题、
//
//定义个学生类Student，包含空参构造、满参构造和以下成员变量：
//
//姓名name(String类型)  成绩 score(ing 类型)
//
//生成以上成员变量的set、get方法
//
//定义测试类，创建三个对象，存入集合中，之后遍历集合list，求出三个学生的平均成绩
public class Student {

    private String name;
    private int score;

    public Student() {
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
