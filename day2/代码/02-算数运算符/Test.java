import java.util.Scanner;

public class Test {
	
	/*
		���󣺼���¼��һ����λ����������Ϊ��λ��ʮλ����λ�󣬴�ӡ�ڿ���̨

		���н��:
			������һ����λ��:
			123
			����123��λΪ:3
			����123ʮλΪ:2
			����123��λΪ:1
	
		������
			1��ʹ��Scanner����¼��һ����λ��
			2����λ�ļ��㣺��ֵ % 10
			3��ʮλ�ļ��㣺��ֵ / 10 % 10
			4����λ�ļ��㣺��ֵ / 100
			5������λ, ʮλ, ��λƴ������ȷ���ַ���, ��ӡ����

	*/
	public static void main(String[] args) {
		// 1��ʹ��Scanner����¼��һ����λ��
		Scanner sc = new Scanner(System.in);
		System.out.println("������һ����λ��");
		int num = sc.nextInt();
		// 2����λ�ļ��㣺��ֵ % 10
		int ge = num % 10;						// 123 % 10 = 3
		// 3��ʮλ�ļ��㣺��ֵ / 10 % 10
		int shi = num / 10 % 10;				// 123 / 10 = 12		12 % 10 = 2
		// 4����λ�ļ��㣺��ֵ / 100
		int bai = num / 100;					// 123 / 100 = 1
		// 5������λ, ʮλ, ��λƴ������ȷ���ַ���, ��ӡ����
		System.out.println("����"+num+"��λΪ:" + ge);
		System.out.println("����"+num+"ʮλΪ:" + shi);
		System.out.println("����"+num+"��λΪ:" + bai);
		
	}
}