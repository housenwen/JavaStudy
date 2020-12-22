public class Demo2Conversion {
	/*
		强制转换: 把一个表示数据范围大的数值或者变量赋值给另一个表示数据范围小的变量
	
		简答理解: 大的给小的, 不能直接给, 需要强转
		
		格式：目标数据类型 变量名 = (目标数据类型)值或者变量;
	*/
	public static void main(String[] args) {
		int a = 10;				// int 4个字节
		byte b = (byte)a;		// byte 1个字节		错误: 不兼容的类型: 从int转换到byte可能会有损失精度
		System.out.println(b);
		
		
		double num1 = 12.9;
		int num2 = (int)num1;
		System.out.println(num2);
	}
}