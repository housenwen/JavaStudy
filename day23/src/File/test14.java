package File;

import java.io.File;
import java.io.IOException;

public class test14 {
    public static void main(String[] args) throws IOException {
        show("d:\\1.txt");
    }
    //1.定义一个show方法,给show方法传入文件路径字符串
    public static void show(String filePath) throws IOException {
        //2.在show方法中根据传入的文件路径创建文件对象(如果当前文件不存在则创建文件对象)
        File file = new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
        //3.调用文件的API获取文件名
        String fileName = file.getName();
        System.out.println("文件名称: "+fileName);
        //4.调用文件的API获取文件的绝对路径
        String absolutePath = file.getAbsolutePath();
        System.out.println("文件的绝对路径: "+absolutePath);
        //5.调用文件的API获取文件的父目录
        String fileParent = file.getParent();
        System.out.println("父目录: "+fileParent);
        //6.调用文件的API获取文件的大小
        long length = file.length();
        System.out.println("文件大小为: "+length+" 字节");
        //7.调用文件的API获取文件的后缀名
        String str = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("扩展名: "+str);
    }
}
