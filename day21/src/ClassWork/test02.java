package ClassWork;

import java.util.Scanner;

/**      请定义main()方法，在main()中按以下顺序要求编写代码：

 Ø  定义一个String[]数组如下：

 String[] arr = {”星期一”,”星期二”,”星期三”,”星期四”,”星期五”,”星期六”,”星期日”};

 Ø  请用户输入一个整数1--7的值：

 Ø  根据用户输入，从数组中取出对应的星期名称，例如：

 用户输入：1

 程序提示：星期一

 Ø  为了防止用户输入小于1或者大于7的值，请使用异常处理从数组中取出对应的“星期名称”，在异常处理中打印：输入错误！！

 */

public class test02 {
    public static void main(String[] args) {

        String[] arr = {"”星期一”","”星期二”","”星期三”","”星期四”","”星期五”","”星期六”","”星期日”"};

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入1-7数字：");
            int index = sc.nextInt()-1;

            if (index < 0 || index > arr.length - 1) {

                throw new ArrayIndexOutOfBoundsException();

            } else {

                String week = arr[index];

                System.out.println("今天是：" + week);

            }
        }
    }
}
