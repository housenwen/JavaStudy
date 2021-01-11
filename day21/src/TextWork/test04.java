package TextWork;

public class test04 {
    public static void main(String[] args){
        int[] arr = null;
       try {
           printArr(arr);
       }catch (NullPointerException e){
           System.out.println(e);
       }
    }

    public static void printArr(int[] arr) throws NullPointerException{
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
