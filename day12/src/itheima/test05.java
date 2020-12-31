package itheima;

public class test05 {
    public static void main(String[] args) {

        NewDriver newDriver = new NewDriver();
        newDriver.go();

        System.out.println("---------------------");

        OldDriver oldDriver = new OldDriver();
        oldDriver.go();

    }
}
