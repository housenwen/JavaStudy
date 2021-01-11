package File;

import java.io.File;

public class test1 {
    public static void main(String[] args) {
        File f = new File("D:\\aaa.txt");
        System.out.println("文件绝对路径:"+f.getAbsolutePath());
        System.out.println("文件构造路径:"+f.getPath());
        System.out.println("文件名称:"+f.getName());
        System.out.println("文件长度:"+f.length()+"字节");

//        File f = new File("d:/aaa/bbb.java");
//        System.out.println("文件绝对路径:"+f.getAbsolutePath());
//        System.out.println("文件构造路径:"+f.getPath());
//        System.out.println("文件名称:"+f.getName());
//        System.out.println("文件长度:"+f.length()+"字节");
        System.out.println("------------------------");
        File f2 = new File("D:\\aaa");
        System.out.println(f2.getAbsolutePath());
        System.out.println(f2.getPath());
        System.out.println(f2.getName());
        System.out.println(f2.length());

    }
}
