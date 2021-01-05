package itheima06;
//1. 自定义人类:包含姓名,年龄,身高属性，私有成员变量,生成无参,有参构造方法,生成get/set方法；
//2. 创建5个人放到ArrayList中；
//3. 使用迭代器获取每个人的信息：找出最高的人,最矮的人，并输出最高人和最矮人的信息。
//   打印格式为：最高的人是张三,身高1.80；最矮的人是李四,身高1.60 。
public class Person {
    private String name;
    private int age;
    private double height;

    public Person() {
    }

    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
