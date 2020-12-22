public class Demo1Operator {
	/*
		
		基本赋值运算符:
			
			= : 将符号右侧的数据, 赋值给左边
			
		扩展赋值运算符:
		
			+=  -=  *=  /=  %=
			
			+= : 将符号左右两边的数据, 做加法运算, 结果赋值给左边
			
	*/
	public static void main(String[] args) {
		int a = 10;
		// += : 将符号左右两边的数据, 做加法运算, 结果赋值给左边
		a += 20;
		System.out.println(a);
	}
}