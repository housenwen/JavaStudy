package TextWork;

public class test01 {
    public static void main(String[] args) {
        try{
            int[] arr = new int[10];
            System.out.println(arr[10]);
            arr = null;
            System.out.println(arr[0]);
        }catch(NullPointerException|ArrayIndexOutOfBoundsException  e2){
            System.out.println("数组角标越界或"+"空指针异常");
            System.out.println();
        }
    }
}
