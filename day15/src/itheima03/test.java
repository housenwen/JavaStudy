package itheima03;

public class test {
    public static void main(String[] args) {

        Animal a = new Dog();
        Person p = new Person("张三",20,a);

        System.out.println(p.toString());

    }
}
