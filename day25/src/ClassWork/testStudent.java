package ClassWork;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testStudent {
    public static void main(String[] args) throws IOException {

        List<Student> list = new ArrayList<>();
        list.add(new Student("张三","男",20,88));
        list.add(new Student("李四","女",19,99));

//        FileOutputStream fos = new FileOutputStream("Student.txt");

        FileWriter fw = new FileWriter("Student.txt",true);

        String s1 = "";

        for (Student s:list){
            s1=s.getName()+s.getGender()+s.getAge()+s.getScore();
            fw.write(s1);
            fw.write("\r\n");
        }

        fw.flush();
        fw.close();





    }
}
