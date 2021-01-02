package itheima06;

public class Student {

    private String name;
    private int age;
    private double height;
    private boolean flag;
    private char sex;

    public Student() {
    }

    public Student(String name, int age, double height, boolean flag, char sex) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.flag = flag;
        this.sex = sex;
    }

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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", flag=" + flag +
                ", sex=" + sex +
                '}';
    }
}
