package Digui;

public class test1 {
    public static void main(String[] args) {

        int num = 5;
        int Value = getValue(num);
        System.out.println(Value);
    }

    private static int getValue(int num) {

        if (num==1){
            return 1;
        }
        return num*(getValue(num-1));
    }
}
