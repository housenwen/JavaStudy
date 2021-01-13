package BufferedWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class test {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("bbb"));

        bw.write("黑马");
        bw.newLine();
        bw.write("程序");
        bw.newLine();
        bw.write("员");
        bw.newLine();

        bw.close();

    }
}
