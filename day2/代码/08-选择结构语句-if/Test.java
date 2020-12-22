import java.util.Scanner;

public class Test {
	/*
		需求：键盘录入学生考试成绩, 根据成绩程序给出不同的奖励。
		
			95~100分 : 自行车一辆
			90~94分  : 游乐场一次
			80~89分	 : 变形金刚一个
			80分以下 : 挨顿揍, 这座城市又多了一个伤心的人~

		步骤:
				1. 使用Scanner录入考试成绩
				2. 判断成绩是否在合法范围内 0~100
					非法的话, 给出错误提示
				3. 在合法的语句块中判断成绩范围符合哪一个奖励
					并给出对应的奖励.
		
	*/
	public static void main(String[] args){
		// 1. 使用Scanner录入考试成绩
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入您的成绩:");
		int score = sc.nextInt();
		// 2. 判断成绩是否在合法范围内 0~100
		if(score >=0 && score <= 100){
			// 合法成绩
			// 3. 在合法的语句块中判断成绩范围符合哪一个奖励
			if(score >= 95 && score <= 100){
				System.out.println("自行车一辆");
			}else if(score >= 90 && score <= 94){
				System.out.println("游乐场一次");
			}else if(score >= 80 && score <= 89){
				System.out.println("变形金刚一个");
			}else {
				System.out.println("挨顿揍, 这座城市又多了一个伤心的人~");
			}
		}else{
			// 非法的话, 给出错误提示
			System.out.println("您的成绩输入有误!");
		}
	}
}