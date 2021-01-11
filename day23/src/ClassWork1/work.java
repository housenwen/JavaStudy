package ClassWork1;

public class work {
    public static void main(String[] args) {

        SuiDao sd = new SuiDao();

        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(sd,"精神小伙"+i);
            t.start();
        }
    }
}
