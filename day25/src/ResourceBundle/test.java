package ResourceBundle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class test {
    public static void main(String[] args)  {

        ResourceBundle rB = ResourceBundle.getBundle("read");

        System.out.println(rB);

    }
}
