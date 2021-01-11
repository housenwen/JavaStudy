package File;

import java.io.File;

public class test9 {
    public static void main(String[] args) {
        File dir = new File("D:\\ccc");
        printDir(dir);
    }

    private static void printDir(File dir) {
        File[] files = dir.listFiles();

        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().contains("java")) {
                    System.out.println("文件名" + f.getAbsolutePath());
                }
            }else {
                printDir(f);
            }
        }
    }
}