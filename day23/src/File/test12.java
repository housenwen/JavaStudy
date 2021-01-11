package File;

import java.io.File;
import java.io.IOException;

public class test12 {
    public static void main(String[] args) throws IOException {

        File f1 = new File("test.txt");
        File f2 = new File("八级目录");
        File f3 = new File("一级目录\\二级目录\\三级目录");

        System.out.println(f1.exists());
        System.out.println(f1.createNewFile());
        System.out.println(f1.exists());

        System.out.println("--------------------------");
        System.out.println(f2.exists());
        System.out.println(f2.mkdir());
        System.out.println(f2.exists());

        System.out.println("---------------------");

        System.out.println(f3.exists());
        System.out.println(f3.mkdirs());
        System.out.println(f3.exists());

        System.out.println(f1.delete());
        System.out.println(f2.delete());
        System.out.println(f3.delete());

    }
}
