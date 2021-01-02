package itheima03;

public class Dog extends Animal{
    @Override
    void eat() {
        System.out.println("狗吃骨头！！！");
    }

    public  String toString(){
        return "一只可爱的小狗！";
    }

    public static void show(){

        System.out.println("一只可爱的小狗！");
    }

}
