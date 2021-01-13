package HomoWork1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class test1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test5_1.txt"));
        Students student = (Students) ois.readObject();
        System.out.println(student.getName() + "," +
                student.getGender() + "," + student.getAge());
        ois.close();
    }
}
