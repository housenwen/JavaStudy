package itheima;
import java.util.Random;
public class test05 {
    public static void main(String[] args) {

        Random random = new Random();


        for(int i=33;i<=66;i++){

            int x=33;
            int y=66;
            int num = random.nextInt(y-x+1)+x;
            System.out.println(num);

        }

    }
}
