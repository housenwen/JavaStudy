package itheima02;

public class Student extends Person{

    public Student() {
    }

    public Student(int id, String name, String sex, String birthday) {
        super(id, name, sex, birthday);
    }

    @Override
    public String getType() {
        return "學生";
    }

    @Override
    public String geyWork() {
        return "學習Java";
    }
}
