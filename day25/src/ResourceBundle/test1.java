package ResourceBundle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class test1 {
    public static void main(String[] args) throws MissingResourceException {

        ResourceBundle rb = ResourceBundle.getBundle("date");

        String name = rb.getString("name");

        String password = rb.getString("password");

        System.out.println(name+"..."+password);

    }
}
