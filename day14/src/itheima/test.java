package itheima;

public class test {
    public static void main(String[] args) {

        Dog d = new Dog();
        Cat c = new Cat();
        Animal s = new Dog();

        showDogEat(d);
        showCatEat(c);
        System.out.println("-----------------------");
        showAnimalEat(c);
        showAnimalEat(d);
        showAnimalEat(s);


    }

    private static void showCatEat(Cat c) {
        c.eat();
    }

    public static void showDogEat(Dog d){
        d.eat();

    };

    public static void showAnimalEat(Animal a){
        a.eat();
    }

}

