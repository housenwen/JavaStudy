package itheima02;

/*
 * 	需求说明：定义如下字符串：
		String str = “javajfiewjavajfiowfjavagkljjava”;
	请分别定义方法统计出：
		1.	字符串中：字符j的数量
		2.	字符串中：字符串java的数量
 */

public class test01 {
    public static void main(String[] args) {
        String str ="javajfiewjavajfiowfjavagkljjava";
        char c = 'j';
        int  count1 = getCount(str,c);
        System.out.println("j的次数"+count1);
        String s = "java";
        int co = getC(str,s);
        System.out.println("java次数"+co);

    }

    private static int getC(String str, String s) {

        //1.定义一个变量，用来记录查找到ch的次数
        int count = 0;
        //2.查找str字符串中出现ch字符的次数
        int index = 0;
        while ((index = str.indexOf(s)) != -1) {//说明查找到了字符串s出现的位置
            count++; //出现的次数累加1
            str = str.substring(index + 1);//更新str字符串中的内容
        }
        return count;

    }

    private static int getCount(String str, char c) {

        int count = 0;

        char[] chars = str.toCharArray();
        System.out.println(chars);

        for (int i = 0; i < chars.length; i++) {
            char c1 = chars[i];
            if (c1==c){
                count++;
            }
        }
        return count;

    }

}
