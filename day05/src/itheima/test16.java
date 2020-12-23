package itheima;

//1.商场推出幸运抽奖活动，抽奖规则如下：
// (1)键盘录入四位数字(1000-9999 的数字),作为顾客的会员卡号
// (2)该会员卡号(键盘录入的四位数字)各位数字之和大于 20，则为幸运客户

import java.util.Scanner;

public class test16 {
    public static void main(String[] args) {
      Scanner scanner =  new Scanner(System.in);
        System.out.println("请输入 4 位会员卡号：");
        int num = scanner.nextInt();

        int g = num%10;
        int s = num/10%10;
        int b = num/100%10;
        int w = num/1000%10;

        int sum = g+s+b+w;
        if (sum>20){
            System.out.println("该会员卡号"+num+"是幸运会员");

        }else {
            System.out.println("该会员卡号"+num+"不是幸运会员");
        }

    }
}
