
import java.util.Random;

public class test11 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int x = 23;
            int y = 79;
            Random sc = new Random();
            int num = sc.nextInt(y-x+1)+x;

            System.out.println(num);
        }




    }
}
