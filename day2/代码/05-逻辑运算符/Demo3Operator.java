public class Demo3Operator {
	/*
		& �� && ������ :
		
			& : ���۷��������true����false, �ұ߶�Ҫ����ִ��
			
			&& : ���ж�·Ч��, �������Ϊfalse��ʱ��, �ұ߾Ͳ�ִ����.
					����������Ϊtrue, �ұ�Ҫ����ִ��.
	*/
	public static void main(String[] args ){
		int x = 3;
		int y = 4;
		// false & true
		System.out.println(++x > 4 && y-- < 5);	// false
		System.out.println("x=" + x);	// 4
		System.out.println("y=" + y);	// 4
	}
}