public class Demo2Conversion {
	/*
		ǿ��ת��: ��һ����ʾ���ݷ�Χ�����ֵ���߱�����ֵ����һ����ʾ���ݷ�ΧС�ı���
	
		������: ��ĸ�С��, ����ֱ�Ӹ�, ��Ҫǿת
		
		��ʽ��Ŀ���������� ������ = (Ŀ����������)ֵ���߱���;
	*/
	public static void main(String[] args) {
		int a = 10;				// int 4���ֽ�
		byte b = (byte)a;		// byte 1���ֽ�		����: �����ݵ�����: ��intת����byte���ܻ�����ʧ����
		System.out.println(b);
		
		
		double num1 = 12.9;
		int num2 = (int)num1;
		System.out.println(num2);
	}
}