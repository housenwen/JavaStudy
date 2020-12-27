package itheima01;



public class test {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog("黑色的","藏獒");

        cat.setColor("花色的");
        cat.setBreed("波斯猫");
        cat.eat();
        cat.catchMouse();

        dog.eat();
        dog.lookHome();

    }
}
