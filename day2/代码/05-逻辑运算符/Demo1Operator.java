public class Demo1Operator {
	/*
		逻辑运算符作用: 用于[整合]多个比较表达式的条件
			
			1. 连接多个比较表达式
			2. 连接true或false
			
		最终得到的结果都是boolean类型的true或false.
		
		应用场景:
			
				需求: 键盘录入学生成绩, 如果是90-100之间, 程序输出[优秀]
					
						判断条件(score >= 90 & score <= 100)
				
				需求: 键盘录入工人工号, 只要3号或者5号或者7号.
				
						判断条件(id == 3 | id == 5 | id == 7)
		
	*/
	public static void main(String[] args){
		int x = 10;
						// true & true
						// x > 5 并且 x < 15
		System.out.println(x > 5 & x < 15);	// true
	}
}