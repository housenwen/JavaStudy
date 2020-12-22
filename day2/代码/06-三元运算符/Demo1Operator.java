public class Demo1Operator {
	/*
		三元运算符:
		
			格式: 关系表达式 ? 表达式1 : 表达式2;
			
		执行流程：
				首先计算关系表达式的值
				如果值为true，取表达式1的值
				如果值为false，取表达式2的值

					
		需求: 求两个变量的最大值
		
	*/
	public static void main(String[] args){
		int a = 10;
		int b = 20;
		int min = a > b ? b : a;
		System.out.println(min);
	}
}