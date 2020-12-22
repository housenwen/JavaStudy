import java.util.Scanner;

public class Test {
	
	/*
		需求：键盘录入一个三位数，将其拆分为个位、十位、百位后，打印在控制台

		运行结果:
			请输入一个三位数:
			123
			整数123个位为:3
			整数123十位为:2
			整数123百位为:1
	
		分析：
			1：使用Scanner键盘录入一个三位数
			2：个位的计算：数值 % 10
			3：十位的计算：数值 / 10 % 10
			4：百位的计算：数值 / 100
			5：将个位, 十位, 百位拼接上正确的字符串, 打印即可

	*/
	public static void main(String[] args) {
		// 1：使用Scanner键盘录入一个三位数
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个三位数");
		int num = sc.nextInt();
		// 2：个位的计算：数值 % 10
		int ge = num % 10;						// 123 % 10 = 3
		// 3：十位的计算：数值 / 10 % 10
		int shi = num / 10 % 10;				// 123 / 10 = 12		12 % 10 = 2
		// 4：百位的计算：数值 / 100
		int bai = num / 100;					// 123 / 100 = 1
		// 5：将个位, 十位, 百位拼接上正确的字符串, 打印即可
		System.out.println("整数"+num+"个位为:" + ge);
		System.out.println("整数"+num+"十位为:" + shi);
		System.out.println("整数"+num+"百位为:" + bai);
		
	}
}