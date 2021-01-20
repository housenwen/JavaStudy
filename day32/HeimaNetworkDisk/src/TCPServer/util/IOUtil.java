package TCPServer.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
    //todo 讀寫複製文件copy方法
    public static void copy(InputStream in, OutputStream out) throws IOException{
        int len;
        byte[] bytes = new byte[1024];
        while ((len=in.read(bytes))!=-1){
//            System.out.println("read len="+len);
            out.write(bytes,0,len);
        }
    }
}
