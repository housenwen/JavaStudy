public class Demo2Operator {
	/*
		�Ķ����д���, �鿴�Ƿ��������, �������ָ��������
	*/
	public static void main(String[] args) {
		short s = 1;
		// s��short����, 1Ĭ����int����
		// short��int���, short��������Ϊint, Ȼ���ٽ�������
		// ����֮��, ������int���, ����int���, �������int, ��int��ֵ��short
		// ��Ҫ����ǿת.
		// s = s + 1;		//  ����: �����ݵ�����: ��intת����short���ܻ�����ʧ
		s = (short)(s + 1);		
		System.out.println(s);
		
		short ss = 1;
		ss += 1;		// ss = (short)(ss + 1);
						// ע��: ��չ��ֵ������ײ���Դ�ǿת����
		System.out.println(ss);
		
	}
}