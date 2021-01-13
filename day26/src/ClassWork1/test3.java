package ClassWork1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class test3 {
    public static void main(String[] args) throws IOException {
        Student s = new Student();
        s.name = "ÖÜ½ÜÂ×";
        s.address="Ì¨ÍåÊ¡";
        s.age = 40;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"));

        oos.writeObject(s);
        oos.close();
        System.out.println("Serialized date is saved");

    }
}
