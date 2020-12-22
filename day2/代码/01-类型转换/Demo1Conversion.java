public class Demo1Conversion {
	/*
		隐式转换: 将数据类型中, 取值范围小的数据, 给取值范围大的类型赋值, 可以直接赋值
	*/
	public static void main(String[] args) {
		int a = 10;			// int 4个字节
		double b = a;		// double 8个字节
		System.out.println(b); //10.0
	}
}