package itheima01;

public class TestStudent {
    public static void main(String[] args) {
        Student xuGan = new Student();
        System.out.println(xuGan.getName()); // 输出:徐干
        System.out.println(xuGan.getAge());// 输出:21
        System.out.println(xuGan.getSex());// 输出： 男
        System.out.println("------------------");
        Student student =new Student("张三",21,'女');
        System.out.println(student.getName());
    }
}
