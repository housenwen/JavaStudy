package File;

import java.io.File;

public class test4 {
    public static void main(String[] args) {
        File f = new File("d:\\aaa\\bbb.txt");
        File f1 = new File("d:\\aaa");

        System.out.println("是否存在"+f.exists());
        System.out.println("是否存在"+f1.exists());

        System.out.println(f1.isFile());
        System.out.println(f1.isDirectory());

    }
}
