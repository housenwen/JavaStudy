public class Demo1Operator {
	/*
		��Ԫ�����:
		
			��ʽ: ��ϵ���ʽ ? ���ʽ1 : ���ʽ2;
			
		ִ�����̣�
				���ȼ����ϵ���ʽ��ֵ
				���ֵΪtrue��ȡ���ʽ1��ֵ
				���ֵΪfalse��ȡ���ʽ2��ֵ

					
		����: ���������������ֵ
		
	*/
	public static void main(String[] args){
		int a = 10;
		int b = 20;
		int min = a > b ? b : a;
		System.out.println(min);
	}
}