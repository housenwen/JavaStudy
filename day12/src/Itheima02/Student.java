package Itheima02;

class Student extends Person{
    private double score = 100;

    public Student() {
        //super(); // 调用父类无参构造器,默认就存在，可以不写，必须再第一行
        System.out.println("子类无参");
    }

    public Student(String name , int age,double score) {
        super(name ,age);// 调用父类有参构造器Person(String name , int age)初始化name和age
        this.score = score;
        System.out.println("子类有参");
    }
    // getter/setter省略

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
