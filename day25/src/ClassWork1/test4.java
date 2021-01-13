package ClassWork1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class test4 {
    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("Student.txt");
        Scanner sc = new Scanner(System.in);
        Properties pro = new Properties();

        while (true){

            System.out.println("请输入学号：");
            String sid = sc.nextLine();
            if (sid.equals("end")){
                break;
            }
            System.out.println("请输入姓名：");
            String name = sc.nextLine();

            if (name.equals("end")){
                break;
            }

            pro.setProperty(sid,name);

        }
        Set<String> strings = pro.stringPropertyNames();

        for (String key :strings){

            System.out.println(key+"=="+pro.getProperty(key));

            fw.write(key+"="+pro.getProperty(key));
            fw.write("\r\n");

        }
        fw.flush();
        fw.flush();












    }
}
