public class Demo1Operator {
	/*
		�����: 
		
			��[����]��[����]���в����ķ���
	
		��������� 
		
			+ - * : ��Сѧ��ѧ�����㷽ʽһ��
			
			/ : �������,���ֻ�ܵõ�����,�����Ҫ�õ�����С���Ľ��,�������С��(��������)������
			
			%(ȡģ) : ȡ����
	*/
	public static void main(String[] args){
		System.out.println(10 + 20);
		System.out.println(10 - 20);
		System.out.println(10 * 20);
		
		System.out.println("-----------------------");
		
		/*
			/ : �������,���ֻ�ܵõ�����,�����Ҫ�õ�����С���Ľ��,�������С��(��������)������
		*/
		System.out.println(10 / 2);		// 5
		System.out.println(10 / 3);		// 3
		System.out.println(10 / 3.0);		// 3.3333333333333335
		System.out.println(10.0 / 3);		// 3.3333333333333335
		
		System.out.println("-----------------------");
		
		/*
			%(ȡģ) : ȡ����
		*/
		
		System.out.println(5 % 2);		// 5 / 2 = ��2, ����Ϊ1
	}
}