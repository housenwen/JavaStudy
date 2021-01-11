package TextWork;

public class test02 {
    public static void main(String[] args) {
        try{
            int[] arr = new int[10];
//            System.out.println(arr[10]);
            arr = null;
            System.out.println(arr[0]);
        }catch(ArrayIndexOutOfBoundsException e2){
            System.out.println("数组角标越界");
        }catch(NullPointerException e){
            System.out.println("空指针异常");
        }
    }
}
