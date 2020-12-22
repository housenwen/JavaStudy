public class Demo3Operator {
	/*
		自增自减运算符 :
			
			++ : 变量自身+1
			-- : 变量自身-1
			
			++ 和-- 既可以放在变量的后边，也可以放在变量的前边。
			
		注意:
		
			单独使用的时候， ++和-- 无论是放在变量的前边还是后边，结果是一样的
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