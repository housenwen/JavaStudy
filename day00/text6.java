import java.util.Scanner;

public class text6{
	
	public static void main(String[] args){
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("������һ����λ��");
	
	int num = sc.nextInt();
	
	
	
	int g = num%10;
	
	int s = num/10%10;
	
	int b = num/100%10;
	
	System.out.println("����ֵ"+num+"�ĸ�λ�ǣ�"+ g);
	
    System.out.println("����ֵ"+num+"��ʮλ�ǣ�"+ s);
	
	System.out.println("����ֵ"+num+"�İ�λ�ǣ�"+ b);
	
	
	}
}
