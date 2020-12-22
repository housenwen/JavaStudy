public class Demo2If {
	/*
		格式：
			if (关系表达式) {
				语句体1;	
			} else {
				语句体2;	
			}

			
		执行流程：
		
			首先计算关系表达式的值
			如果关系表达式的值为true就执行语句体1
			如果关系表达式的值为false就执行语句体2
			继续执行后面的语句内容
				
	*/
	public static void main(String[] args) {
		// 程序判断一个数, 是奇数还是偶数
		int num = 9;
		
		if(num % 2 == 0){
			System.out.println("偶数");
		}else{
			System.out.println("奇数");
		}
	}
}