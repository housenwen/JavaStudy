package File;
/**
 * 2 构造方法
 *
 * - public File(String pathname) ：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
 * - public File(String parent, String child) ：从父路径名字符串和子路径名字符串创建新的 File实例。
 * - public File(File parent, String child) ：从父抽象路径名和子路径名字符串创建新的 File实例。
 */

import java.io.File;

public class test {
    public static void main(String[] args) {

        String pathname = "D:\\aaa.txt";
        File f1 = new File(pathname);

        String pathname1 = "D\\aaa\\bbb.txt";
        File f2 = new File(pathname1);

        String parent = "D:\\aaa";
        String child = "bbb.txt";

        File f3 = new File(parent,child);

        File parentDir = new File("D:\\aaa");
        String child1 = "bbb.txt";

        File f4 = new File(parentDir,child1);


    }
}
