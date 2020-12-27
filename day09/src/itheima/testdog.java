package itheima;

public class testdog {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.color = "花色的";
        cat.breed = "波斯猫";
        cat.eat();
        cat.catchMouse();

        Dog dog = new Dog();
        dog.color = "黑色的";
        dog.breed = "藏獒";
        dog.eat();
        dog.lookHome();
    }
}
