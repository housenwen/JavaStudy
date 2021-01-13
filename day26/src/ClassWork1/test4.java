package ClassWork1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class test4 {
    public static void main(String[] args) throws IOException {

        Student s = null;

        FileInputStream fis = new FileInputStream("student.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            s = (Student) ois.readObject();
            ois.close();
            fis.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Student class not found");
            e.printStackTrace();
            return;

        }
        System.out.println(s);

    }
}
