package itheima;

public class test01 {
    public static void main(String[] args) {

        Animal a = new Cat();
        a.eat();
        Animal a1 = new Dog();
        a1.eat();

        Cat c = (Cat) a;
        c.CatchMouse();

        Dog d = (Dog) a1;
        d.LookHome();

        System.out.println("----------------------");
        Animal a3 = new Cat();

        if (a3 instanceof Cat){
            ((Cat) a3).CatchMouse();
        }else if(a3 instanceof Dog) {
            ((Dog) a3).LookHome();
        }

    }
}
