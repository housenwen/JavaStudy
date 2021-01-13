package ClassWork2;

import ClassWork1.Student;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {

        List<Student> list = new ArrayList<>();

        FileInputStream fis  = new FileInputStream("LOL.txt");

        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            list = (List<Student>) ois.readObject();
            fis.close();
            ois.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Student class no found!");
            e.printStackTrace();
            return;
        }

        for (Student student :list){
            System.out.println(student);
        }

    }
}
