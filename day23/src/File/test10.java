package File;

import java.io.File;

public class test10 {
    public static void main(String[] args) {
        File file1 = new File("d:\\test1.txt");
        File file2 = new File("d:\\测试目录");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file2.getAbsolutePath());

        System.out.println(file1.getName());
        System.out.println(file2.getName());

        System.out.println(file1.getParent());
        System.out.println(file2.getParent());

        System.out.println(file1.length());
        System.out.println(file2.length());

        System.out.println(file1.isFile());
        System.out.println(file2.isFile());

        System.out.println(file1.isDirectory());
        System.out.println(file2.isDirectory());

        // 5. 分别判断file1和file2是否是文件？是否是目录？
        System.out.printf("file1 是否是文件 %b 是否是目录 %b\n", file1.isFile(), file1.isDirectory());
        System.out.printf("file2 是否是文件 %b 是否是目录 %b\n", file2.isFile(), file2.isDirectory());
    }

}
