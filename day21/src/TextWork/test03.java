package TextWork;

public class test03 {
    public static void main(String[] args){
        int[] arr = null;
        printArr(arr);
    }

    public static void printArr(int[] arr) throws NullPointerException{
        try{
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }catch (NullPointerException e){
            System.out.println(e+"啥也不做");
        }

    }
}
