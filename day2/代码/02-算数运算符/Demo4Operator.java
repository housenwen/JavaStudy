public class Demo4Operator {
	/*
		参与操作:
		
			++在前: 先对该变量做自增(++)或者自减(--)，然后再拿变量参与操作。
		
			++在后:	先将该变量原本的值，取出来参与操作，随后再进行自增(++)，自减(--)。
	*/
	public static void main(String[] args){
		// ++在前: 先对该变量做自增(++)或者自减(--)，然后再拿变量参与操作。
		int a = 10;
		int b = ++a;
		
		System.out.println(a);		// 11
		System.out.println(b);		// 11
		
		
		// ++在后:	先将该变量原本的值，取出来参与操作，随后再进行自增(++)，自减(--)。
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