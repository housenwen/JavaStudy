package itheima01;

public class test02 {
    public static void main(String [] args){
        int layer = 5;
        for (int i = 1; i <= layer; i++) {
            int spaceNum = layer - i;
            for (int j = 1; j <= spaceNum; j++) {
                System.out.print(" ");
            }
            int starNum = 2 * i - 1;
            for (int j = 1; j <= starNum; j++) {
                System.out.print("*");
            }
            System.out.println(" ");
        }}
}

