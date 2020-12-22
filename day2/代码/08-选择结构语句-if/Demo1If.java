public class Demo1If {
	/*
		格式：
			if (关系表达式) {
				语句体;	
			}
			
		执行流程：
		
			首先计算关系表达式的值
			如果关系表达式的值为true就执行语句体
			如果关系表达式的值为false就不执行语句体
			继续执行后面的语句内容

		注意事项:
		
			1. 如果if语句所控制的语句体, 是一条语句, 大括可以省略不写
				但是不建议!
				
			2. if语句的小括后面, 不要写分号
				
	*/
	public static void main(String[] args) {
		System.out.println("开始");
		
		// 如果年龄大于18岁, 就可以上网吧
		int age = 17;
		
		if(age >= 18){
			// int a = 10;
			System.out.println("可以上网吧");
		}
			
		System.out.println("结束");
	}
}