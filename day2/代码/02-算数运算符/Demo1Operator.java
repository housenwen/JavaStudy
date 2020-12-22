public class Demo1Operator {
	/*
		运算符: 
		
			对[常量]或[变量]进行操作的符号
	
		算数运算符 
		
			+ - * : 跟小学数学的运算方式一样
			
			/ : 整数相除,结果只能得到整数,如果想要得到带有小数的结果,必须加入小数(浮点类型)的运算
			
			%(取模) : 取余数
	*/
	public static void main(String[] args){
		System.out.println(10 + 20);
		System.out.println(10 - 20);
		System.out.println(10 * 20);
		
		System.out.println("-----------------------");
		
		/*
			/ : 整数相除,结果只能得到整数,如果想要得到带有小数的结果,必须加入小数(浮点类型)的运算
		*/
		System.out.println(10 / 2);		// 5
		System.out.println(10 / 3);		// 3
		System.out.println(10 / 3.0);		// 3.3333333333333335
		System.out.println(10.0 / 3);		// 3.3333333333333335
		
		System.out.println("-----------------------");
		
		/*
			%(取模) : 取余数
		*/
		
		System.out.println(5 % 2);		// 5 / 2 = 商2, 余数为1
	}
}