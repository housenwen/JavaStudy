public class Demo4Operator {
	/*
		�������:
		
			++��ǰ: �ȶԸñ���������(++)�����Լ�(--)��Ȼ�����ñ������������
		
			++�ں�:	�Ƚ��ñ���ԭ����ֵ��ȡ�����������������ٽ�������(++)���Լ�(--)��
	*/
	public static void main(String[] args){
		// ++��ǰ: �ȶԸñ���������(++)�����Լ�(--)��Ȼ�����ñ������������
		int a = 10;
		int b = ++a;
		
		System.out.println(a);		// 11
		System.out.println(b);		// 11
		
		
		// ++�ں�:	�Ƚ��ñ���ԭ����ֵ��ȡ�����������������ٽ�������(++)���Լ�(--)��
		int aa = 10;
				//bb = 10
		int bb = aa++;	// aa = 11
		
		System.out.println(aa);	// 11
		System.out.println(bb); // 10
		
		int num = 123;
		System.out.println(num++);	// 123
		System.out.println(num);	// 124	
		
		System.out.println(10++);	// a++;  a = a + 1;
									// 10++; 10 = 10 + 1;
	}
}