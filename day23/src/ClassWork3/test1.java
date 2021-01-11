package ClassWork3;

public class test1 {
    public static void main(String[] args) {

        RunnableS s = new RunnableS();

        for (int i = 1; i <= 10; i++) {

            Thread t = new Thread(s,"精神小伙"+i+"号");
            t.start();

        }

    }
}
