package HW;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student"));

        List<Student> list = new ArrayList<>();
        try {
            list =(List<Student>) ois.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();

        for (Student s:list){
            System.out.println(s);
        }
    }
}
