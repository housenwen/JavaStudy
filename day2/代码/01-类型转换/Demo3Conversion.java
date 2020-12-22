public class Demo3Conversion {
	/*
		请找出下列代码的错误, 指出原因并解决.
	*/
	public static void main(String[] args) {
		byte a = 3;
		byte b = 4;
		/*
			前置知识点铺垫:
			
				隐式转换: 当小的数据类型和大的数据类型在一起运算的时候, 小的会先提升为大的之后, 再进行运算
							特殊关注: byte short char 在运算的时候, 都会直接提升为int, 然后再进行运算.
							
			错误原因:
				byte c = a + b;
				
				这里的a和b是两个byte类型, 它们在运算之前会将自己提升为int类型, 然后再进行运算
				两个int相加, 结果还是int, 想要把一个int类型的结果, 赋值给byte类型的变量
				
						大的给小的, 不能直接给.
						
						非要给的话, 就需要强制类型转换.
			解决方案:

				首先让a和b进行相加, 使用()提升算数优先级
				
				再对相加的结果整体进行强转.
						
		*/
		byte c = (byte)(a + b);		// 错误: 不兼容的类型: 从int转换到byte可能会有损失
		System.out.println(c);
	}
}