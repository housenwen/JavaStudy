package CK;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test3 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        ArrayList<Student> list1 = new ArrayList<>();
        try {
            list = FileUtils.readLines(new File("text2_1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String str:list){
        String[] strings= str.split(",");
        list1.add(new Student(strings[0],Integer.parseInt(strings[1])));
        }
        for (Student s:list1){
            System.out.println(s);
        }

    }
}
