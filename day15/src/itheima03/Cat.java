package itheima03;

public class Cat extends Animal{

    @Override
    void eat() {
        System.out.println("猫吃鱼鱼！");
    }

    public  String toString(){
        return "一只可爱的小猫猫！";
    }
    public static void show(){
        System.out.println("一只可爱的小猫猫！");
    }

}
