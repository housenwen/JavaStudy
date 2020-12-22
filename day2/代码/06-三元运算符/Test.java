public class Test {
	/*
		1. 定义三个变量用于保存和尚的身高
		2. 用三元运算符 , 比较前两个变量，获取较大值。
		3. 用三元运算符 , 让较大值和第三个变量比较，获取最大值。
		4. 输出结果

	*/
	public static void main(String[] args) {
		// 1. 定义三个变量用于保存和尚的身高
		int a = 150;
		int b = 210;
		int c = 165;
		
		// 2. 用三元运算符 , 比较前两个变量，获取较大值。
		int tempMax = a > b ? a : b;
		
		// 3. 用三元运算符 , 让较大值和第三个变量比较，获取最大值。
		int max = tempMax > c ? tempMax : c;
		
		// 4. 输出结果
		System.out.println(max);
		
		int result = a > b ? a : b > c ? a > b ? a : b : c;
		
		System.out.println(result);
	}
}