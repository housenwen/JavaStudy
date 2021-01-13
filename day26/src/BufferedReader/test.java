package BufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("copy.txt"));

        String line =null;

        while ((line=br.readLine())!=null){
            System.out.println(line);
            System.out.println("-----------------");
        }

        br.close();


    }
}
