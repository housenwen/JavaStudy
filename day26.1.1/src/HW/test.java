package HW;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException, NotSerializableException {

        List<Student> stuList = new ArrayList<>();

        Student s1 = new Student("“迪丽热巴”","”女”",18,99);
        Student s2 = new Student("“古力娜扎”","”女”",19,98);
        Student s3 = new Student("“周杰伦”","”男”",20,88);
        Student s4 = new Student("“蔡徐坤”","”男”",19,78);

        stuList.add(s1);
        stuList.add(s2);
        stuList.add(s3);
        stuList.add(s4);

        FileOutputStream fos = new FileOutputStream("student");

        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(stuList);
        oos.close();

    }
}
