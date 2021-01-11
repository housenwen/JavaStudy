package Digui;

public class test3 {
    public static void main(String[] args) {
        System.out.println(diGui(10));
    }

    public static int diGui(int n){
        if(n<=2){
            return 1;
        }
        return diGui(n-1) + diGui(n-2);
    }
}
