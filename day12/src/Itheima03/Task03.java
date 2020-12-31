package Itheima03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 	需求说明：将文件name.txt中的数据“卢俊义”修改为：“史文恭”。
  		在项目目录下创建文件name.txt，并写入以下内容：
		it001,武松,男,22
		it002,林冲,男,20
		it003,卢俊义,男,18
		it004,石宝,男,23

		读文件--> 解析每行数据封装为Student对象 --> 把每个对象存集合中 --> 判断集合中是否有“卢俊义”，有就修改为：“史文恭” --> 把集合中的内容写入到文件中
 */
public class Task03 {
    public static void main(String[] args) throws IOException {
        //定义一个用来存学生的集合
        ArrayList<Student> list = new ArrayList<Student>();
        //1.读文件
        BufferedReader in = new BufferedReader(new FileReader("name.txt"));
        //2.解析每行数据封装为Student对象
        String line = null;
        while( (line = in.readLine()) != null  ){
            //it001,武松,男,22
            String[] arr = line.split(",");
            //发现没有学生类，定义学生类，然后再创建对象
            //Student s = new Student(id, name, sex, age);
            Student s = new Student(arr[0], arr[1], arr[2], arr[3]);
            //把每个对象存集合中
            list.add(s);
        }

        //3.判断集合中是否有“卢俊义”，有就修改为：“史文恭”
        for (int i = 0; i < list.size(); i++) {
            //1.获取集合中的每个Student对象
            //it003,卢俊义,男,18
            Student s = list.get(i);
            //2.判断否有“卢俊义”，有就修改为：“史文恭”
            if (s.getName().equals("卢俊义")) {
                s.setName("史文恭");
            }
        }

        //4.把集合中的内容写入到文件中
        //写数据到文件中
        BufferedWriter out = new BufferedWriter(new FileWriter("name.txt"));
        //集合中的内容写入到文件中
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            //it003,卢俊义,男,18
            line = s.getId() +","+s.getName() +","+ s.getSex() +"," + s.getAge();
            out.write(line);
            out.newLine();
            out.flush();
        }

        //5.关闭流
        in.close();
        out.close();
    }
}

