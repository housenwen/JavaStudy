public class Demo1Operator {
	/*
		�߼����������: ����[����]����Ƚϱ��ʽ������
			
			1. ���Ӷ���Ƚϱ��ʽ
			2. ����true��false
			
		���յõ��Ľ������boolean���͵�true��false.
		
		Ӧ�ó���:
			
				����: ����¼��ѧ���ɼ�, �����90-100֮��, �������[����]
					
						�ж�����(score >= 90 & score <= 100)
				
				����: ����¼�빤�˹���, ֻҪ3�Ż���5�Ż���7��.
				
						�ж�����(id == 3 | id == 5 | id == 7)
		
	*/
	public static void main(String[] args){
		int x = 10;
						// true & true
						// x > 5 ���� x < 15
		System.out.println(x > 5 & x < 15);	// true
	}
}