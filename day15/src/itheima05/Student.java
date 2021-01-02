package itheima05;

public class Student {
   private String name;
   private String sex;
   private int age;
   private double sorce;

    public Student() {
    }

    public Student(String name, String sex, int age, double sorce) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sorce = sorce;
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

    public double getSorce() {
        return sorce;
    }

    public void setSorce(double sorce) {
        this.sorce = sorce;
    }
}
