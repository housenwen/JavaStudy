package Digui;
import java.io.File;

import static File.test8.printDir;

public class test2 {
    public static void main(String[] args) {

            // 创建File对象
            File dir  = new File("D:\\aaa");
            // 调用打印目录方法
            printDir(dir);
        }

        public static void printDir(File dir){
            // 获取子文件和目录
            File[] files = dir.listFiles();

            // 循环打印
            for (File file : files) {
                if (file.isFile()) {
                    // 是文件，判断文件名并输出文件绝对路径
                    if (file.getName().endsWith(".java")) {
                        System.out.println("文件名:" + file.getAbsolutePath());
                    }
                } else {
                    // 是目录，继续遍历,形成递归
                    printDir(file);
                }
        }
    }
}
