import java.util.Scanner;

public class text7{
	
	public static void main (String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入您的成绩：");
	
	    int num = sc.nextInt();
		
		if(num>=0&&num<=100){
			
			if(num>=95&&num<=100){
				
				System.out.println("自行车一辆");
				
			} else if(num>=90&&num<=94){
				
				System.out.println("游乐场一次");
				
			} else if(num>=80&&num<=89){
				
				System.out.println("变形金刚一个");
				
			}else{
				
				System.out.println("胖揍一顿");
				
			}
			
			
			
		}else{
			
			
			System.out.println("您的输入有误");
			
		}
		
		
		
	}
	
	
	
	
	
}