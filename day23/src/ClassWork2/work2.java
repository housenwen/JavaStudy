package ClassWork2;

interface Animal {
    public abstract void eat(String some);
}

class Feeder {
    public static void feed(Animal a,String some){
        a.eat(some);
    }
}