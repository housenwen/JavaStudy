public class Demo4Conversion {
	/*
		请找出下列代码的错误, 指出原因并解决.
		
		担心的问题:
				
			3 和 4 是两个常量, 整数常量默认的数据类型是int
			这里两个int相加, 结果还是int, int给byte赋值. 
			emmm... 应该需要强转吧
		
		Java存在常量优化机制:
		
			byte d = 3 + 4;
			
			这里的3和4是两个常量, Java存在常量优化机制, 会在编译的时候就会让3和4进行相加, 然后会自动判断7是否在byte的取值范围内
			
					不在范围内: 编译出错
					
					在范围内: 通过编译
	*/
	public static void main(String[] args) {
		byte d = 3 + 4;
		System.out.println(d);
		
		long num = 123456789123456789L;
	}
}