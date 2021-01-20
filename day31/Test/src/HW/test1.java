package HW;

import java.io.File;

public class test1 {
    public static void main(String[] args){
        File dir  = new File("d:\\多级目录");

       long max =  getFileSize(dir);
        System.out.println(max);
    }

    public static long getFileSize(File dir){
        //TODO 计算文件夹的大小
        if (dir.isFile()){
            return dir.length();
        }
        return dir.length();
    }
}
