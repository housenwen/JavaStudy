package ClassWork2;

import ClassWork1.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {

        Student s1 = new Student("����","��������",30);
        Student s2 = new Student("����","��ŷ����",24);
        Student s3 = new Student("ŵ��","ŵ����˹",35);

//        s1.setName = "����";
//        s1.address = "��������";
//        s1.age = 30;
//        s2.name = "����";
//        s2.address = "��������";
//        s2.age = 20;
//        s3.name = "ŵ��";
//        s3.address = "ŵ����˹";
//        s3.age = 31;

        ArrayList<Student> list = new ArrayList<>();

        list.add(s1);
        list.add(s2);
        list.add(s3);

        System.out.println(list);

        FileOutputStream fos = new FileOutputStream("LOL.txt");

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(list);

        fos.close();
        oos.close();

        System.out.println("Serialized date is saved");

    }
}
