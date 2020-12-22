public class Demo3Operator {
	/*
		& 和 && 的区别 :
		
			& : 无论符号左边是true还是false, 右边都要继续执行
			
			&& : 具有短路效果, 符号左边为false的时候, 右边就不执行了.
					如果符号左边为true, 右边要继续执行.
	*/
	public static void main(String[] args ){
		int x = 3;
		int y = 4;
		// false & true
		System.out.println(++x > 4 && y-- < 5);	// false
		System.out.println("x=" + x);	// 4
		System.out.println("y=" + y);	// 4
	}
}