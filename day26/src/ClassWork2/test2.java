package ClassWork2;

import java.io.IOException;
import java.io.PrintStream;

public class test2 {
    public static void main(String[] args) throws IOException {
//        System.out.println(97);
        PrintStream ps = new PrintStream("ps.txt");

        System.setOut(ps);

        System.out.println(97);

    }
}
