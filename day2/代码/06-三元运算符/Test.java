public class Test {
	/*
		1. ���������������ڱ�����е����
		2. ����Ԫ����� , �Ƚ�ǰ������������ȡ�ϴ�ֵ��
		3. ����Ԫ����� , �ýϴ�ֵ�͵����������Ƚϣ���ȡ���ֵ��
		4. ������

	*/
	public static void main(String[] args) {
		// 1. ���������������ڱ�����е����
		int a = 150;
		int b = 210;
		int c = 165;
		
		// 2. ����Ԫ����� , �Ƚ�ǰ������������ȡ�ϴ�ֵ��
		int tempMax = a > b ? a : b;
		
		// 3. ����Ԫ����� , �ýϴ�ֵ�͵����������Ƚϣ���ȡ���ֵ��
		int max = tempMax > c ? tempMax : c;
		
		// 4. ������
		System.out.println(max);
		
		int result = a > b ? a : b > c ? a > b ? a : b : c;
		
		System.out.println(result);
	}
}