package FileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class test {
    public static void main(String[] args) throws IOException {

        File file = new File("Job");

        FileWriter fw = new FileWriter(file);

        fw.write(97);
        fw.write('b');
        fw.write('c');
        fw.write(30001);

        fw.flush();
//        fw.close();

        FileWriter fw2 = new FileWriter("King");


    }
}
