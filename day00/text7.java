import java.util.Scanner;

public class text7{
	
	public static void main (String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("���������ĳɼ���");
	
	    int num = sc.nextInt();
		
		if(num>=0&&num<=100){
			
			if(num>=95&&num<=100){
				
				System.out.println("���г�һ��");
				
			} else if(num>=90&&num<=94){
				
				System.out.println("���ֳ�һ��");
				
			} else if(num>=80&&num<=89){
				
				System.out.println("���ν��һ��");
				
			}else{
				
				System.out.println("����һ��");
				
			}
			
			
			
		}else{
			
			
			System.out.println("������������");
			
		}
		
		
		
	}
	
	
	
	
	
}