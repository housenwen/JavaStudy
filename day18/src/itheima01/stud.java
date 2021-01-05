package itheima01;

import Sex.Sex;

public class stud extends Student{
    public stud(int id, String name, Sex sex, String birthday, int age) {
        super(id, name, sex, birthday, age);
    }

    public stud() {
    }

    @Override
    public String getType() {
        return "学生";
    }

    @Override
    public String getWork() {
        return "学习Java";
    }
}
