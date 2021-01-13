package BufferedInPutStream;

import java.io.*;

public class test2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 创建流对象
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("jdk8.txt"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.txt"));
        ){
            // 读写数据
            int len;
            byte[] bytes = new byte[8*1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0 , len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("缓冲流使用数组复制时间:"+(end - start)+" 毫秒");
    }
}
