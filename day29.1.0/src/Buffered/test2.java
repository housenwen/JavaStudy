package Buffered;

import java.io.*;

public class test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new FileReader("test.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
        String str = br.readLine();
        if(str !=null){
            bw.write("“官网：”"+str);
            bw.flush();
        }
        br.close();
        bw.close();
    }
}
