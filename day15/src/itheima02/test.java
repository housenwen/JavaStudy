package itheima02;

public class test {
    public static void main(String[] args) {
        String str = "张三,20,男,13513153355";
        String[] arr = str.split(",");
        for(int i = 0;i < arr.length ; i++){
            System.out.println(arr[i]);
        }

    }
}
