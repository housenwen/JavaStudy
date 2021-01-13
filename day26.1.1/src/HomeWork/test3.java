package HomeWork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("temp.txt"));

        List<String> list = new ArrayList<>();

        String str = null;

        while ((str=br.readLine())!=null){
            list.add(str);
        }
        br.close();

        System.out.println(list);
    }
}
