package CK;

import org.apache.commons.io.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class test6 {
    public static void main(String[] args) throws IOException {
        Student student = new Student("周杰伦",23);
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("test5_1.txt"));
        oos.writeObject(student);
        oos.close();

    }
}
