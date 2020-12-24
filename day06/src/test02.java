import java.util.Scanner;
public class test02 {
    public static void main(String[] args) {
        int[] arr = {5,8,9,31,7};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您要查找的一个元素：");
        int num = scanner.nextInt();

        int index = -1;

        for (int i = 0; i < arr.length; i++) {

            if (num==arr[i]){

                index = i;
                break;

            }

        }

        System.out.println("该数字在数组中的索引是"+index);
    }
}
