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

        Student s1 = new Student("赵信","德玛西亚",30);
        Student s2 = new Student("亚索","艾欧尼亚",24);
        Student s3 = new Student("诺手","诺克萨斯",35);

//        s1.setName = "赵信";
//        s1.address = "德玛西亚";
//        s1.age = 30;
//        s2.name = "盖伦";
//        s2.address = "德玛西亚";
//        s2.age = 20;
//        s3.name = "诺手";
//        s3.address = "诺克萨斯";
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
