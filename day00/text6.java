import java.util.Scanner;

public class text6{
	
	public static void main(String[] args){
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("请输入一个三位数");
	
	int num = sc.nextInt();
	
	
	
	int g = num%10;
	
	int s = num/10%10;
	
	int b = num/100%10;
	
	System.out.println("该数值"+num+"的个位是："+ g);
	
    System.out.println("该数值"+num+"的十位是："+ s);
	
	System.out.println("该数值"+num+"的百位是："+ b);
	
	
	}
}
