package File;

import java.io.File;

public class test11 {
    public static void main(String[] args) {
        File file = new File("d:\\多级目录");
        File [] files = file.listFiles();

        for (File f : files){
            if (f.isFile()){
                System.out.println("文件"+f.getAbsolutePath());
            }else if (f.isDirectory()){
                System.out.println("目录"+f.getAbsolutePath());
            }
        }
    }
}
