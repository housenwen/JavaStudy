public class Demo2Operator {
	/*
		逻辑运算符分类 :
		
			&(与) : 并且, 遇false则false, 只有符号左右两边同时为true, 结果才为true.
							
			|(或) : 或者, 遇true则true, 只有符号左边两边同时为false, 结果才为false
			
			!(非) : 取反
			
			^(异或) : 相同为false, 不同为true.
			
	*/
	public static void main(String[] args){
		// &(与) : 并且
		System.out.println(true & true);		// true
		System.out.println(false & false);		// false
		System.out.println(true & false);		// false
		System.out.println(false & true);		// false
		System.out.println("------------------");
		// |(或) : 或者
		System.out.println(true | true);		// true
		System.out.println(false | false);		// false
		System.out.println(true | false);		// true
		System.out.println(false | true);		// true
		System.out.println("------------------");
		// !(非) : 取反
		System.out.println(!true);				// false
		System.out.println(!!true);				// true
		System.out.println("------------------");
		// ^(异或) :
		System.out.println(true ^ true);		// false
		System.out.println(false ^ false);		// false
		System.out.println(true ^ false);		// true
		System.out.println(false ^ true);		// true
	}
}