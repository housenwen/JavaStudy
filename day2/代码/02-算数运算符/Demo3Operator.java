public class Demo3Operator {
	/*
		�����Լ������ :
			
			++ : ��������+1
			-- : ��������-1
			
			++ ��-- �ȿ��Է��ڱ����ĺ�ߣ�Ҳ���Է��ڱ�����ǰ�ߡ�
			
		ע��:
		
			����ʹ�õ�ʱ�� ++��-- �����Ƿ��ڱ�����ǰ�߻��Ǻ�ߣ������һ����
	*/
	public static void main(String[] args){
		int a = 10;
		++a;						// a = a + 1;
		System.out.println(a);		// 11
		
		int b = 10;
		--b;
		System.out.println(b);		// 9
	}
}