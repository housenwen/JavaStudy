public class Demo3If {
	/*
		格式：
			if (判断条件1) {
				语句体1;	
			} else if (判断条件2) {
				语句体2;	
			} 
			…
			else {
				语句体n+1;
			}
			
		执行流程：
			首先计算判断条件1的值
			如果值为true就执行语句体1；如果值为false就计算判断条件2的值
			如果值为true就执行语句体2；如果值为false就计算判断条件3的值
			…
			如果没有任何判断条件为true，就执行语句体n+1。
			
		需求:
			根据学生成绩, 程序给出对应评价
			
			90~100
				优秀
			80~89
				良好
			70~79
				中等
			60~69
				及格
			0~59
				请努力加油!
			
	*/
	public static void main(String[] args){
		int score = -1;
		
		if(score >= 90 && score <= 100){
			System.out.println("优秀");
		}else if (score >= 80 && score <= 89){
			System.out.println("良好");
		}else if (score >= 70 && score <= 79){
			System.out.println("中等");
		}else if (score >= 60 && score <= 69){
			System.out.println("及格");
		}else if (score >= 0 && score <= 59){
			System.out.println("请努力加油");
		}else{
			System.out.println("成绩有误!");
		}
	}
}







