public class Demo2Operator {
	/*
		�߼���������� :
		
			&(��) : ����, ��false��false, ֻ�з�����������ͬʱΪtrue, �����Ϊtrue.
							
			|(��) : ����, ��true��true, ֻ�з����������ͬʱΪfalse, �����Ϊfalse
			
			!(��) : ȡ��
			
			^(���) : ��ͬΪfalse, ��ͬΪtrue.
			
	*/
	public static void main(String[] args){
		// &(��) : ����
		System.out.println(true & true);		// true
		System.out.println(false & false);		// false
		System.out.println(true & false);		// false
		System.out.println(false & true);		// false
		System.out.println("------------------");
		// |(��) : ����
		System.out.println(true | true);		// true
		System.out.println(false | false);		// false
		System.out.println(true | false);		// true
		System.out.println(false | true);		// true
		System.out.println("------------------");
		// !(��) : ȡ��
		System.out.println(!true);				// false
		System.out.println(!!true);				// true
		System.out.println("------------------");
		// ^(���) :
		System.out.println(true ^ true);		// false
		System.out.println(false ^ false);		// false
		System.out.println(true ^ false);		// true
		System.out.println(false ^ true);		// true
	}
}