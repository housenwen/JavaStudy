public class Demo2Operator {
	/*
		字符串的 + 操作
		
			当 + 操作中出现字符串时，这个 + 是[字符串连接符]，而不是算术运算。

	*/
	public static void main(String[] args) {
		// 字符串可以使用 +号, 跟[任意数据类型]拼接
		System.out.println("itheima" + 666);
		System.out.println("itheima" + true);
		
		System.out.println(1 + 99 + "年黑马");
		System.out.println("5+5="+5+5);				// 5+5=55
		System.out.println("5+5="+(5+5));			// 5+5=10
	}
}