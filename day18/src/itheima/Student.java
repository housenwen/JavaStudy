package itheima;

public class Student {
    private String name;
    private int age ;
    private num num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public itheima.num getNum() {
        return num;
    }

    public void setNum(itheima.num num) {
        this.num = num;
    }

    public Student(String name, int age, itheima.num num) {
        this.name = name;
        this.age = age;
        this.num = num;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", num=" + num +
                '}';
    }
}
