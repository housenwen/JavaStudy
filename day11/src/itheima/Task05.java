package itheima;
import java.util.ArrayList;

/*
 * 分析以下需求，并用代码实现
		1.定义ArrayList集合，存入多个字符串"abc" "def"  "efg" "def" "def" "qwe" "def" "def" "swd" "wwe" "def" "def"
		2.使用普通for循环获取集合中索引为3的元素并打印
		3.统计集合中包含字符串"def"的数量
		4.定义方法public static boolean myContains(ArrayList list,String str)
			(1)参数
				a.ArrayList list: 表示存储多个String数据的集合
				b.String str: 表示一个字符串
			(2)返回值
				true: 表示list集合中包含字符串str
				false: 表示list集合中不包含字符串str

		5.删除集合中的所有字符串"def"(思路:循环判断集合中是否包含"def"字符串,包含就删除,可以利用上面定义的myContains方法)
		6.将集合中每个元素中的小写字母变成大写字母
		7.遍历打印最终的集合中的元素
 */
public class Task05{
    public static void main(String[] args) {
        ArrayList<String> list = getArrayList();
        //获取集合中索引为3的元素
        int index = 3;
        String element = getElementByIndex(list, index);
        System.out.println("索引为 "+index+"的元素是 "+ element);
        //统计集合中包含字符串def的个数
        String str = "def";
        int count = getCountStr(list,str);
        System.out.println("集合中包含字符串"+str+"的数量为 "+count + " 个");
        //删除集合中的所有字符串def
        delAllStr(list,str);
        System.out.println(list);
        //将集合中元素中的小写字母变成大写字母
        toUpperCase(list);
        //打印集合元素
        printArrayList(list);


    }

    //getArrayList方法，返回存储多个字符串的ArrayList集合对象
    public static ArrayList<String> getArrayList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("def");
        list.add("efg");
        list.add("def");
        list.add("def");
        list.add("qwe");
        list.add("def");
        list.add("def");
        list.add("swd");
        list.add("wwe");
        list.add("swd");
        list.add("def");
        list.add("def");
        list.add("swd");
        return list;
    }
    //根据索引，获取list集合中该索引对应的元素
    public static String getElementByIndex(ArrayList<String> list,int index) {
        //其实根据索引获取元素，根本不用循环可以直接调用get方法
//		return list.get(index);
        for(int i = 0;i<list.size();i++) {
            if(i==index)
                return list.get(index);
        }
        return null;
        //以上代码如果考虑索引是否越界，就需要添加额外的判断
    }
    /**
     * 判断集合list中是否包含str，包含返回true，不包含返回false
     * @param list
     * @param str
     * @return
     */
    public static boolean myContains(ArrayList<String> list,String str) {
        //遍历集合
        for(int i = 0;i<list.size();i++) {
            //获取当前元素
            String thisStr = list.get(i);
            //判断当前元素和要查找的元素是否相等
            if(thisStr.equals(str))
                return true;
        }
        //如果遍历完成后仍然没有返回true，则认为没有找到，返回false
        return false;
    }
    //在list集合中统计str的数量
    private static int getCountStr(ArrayList<String> list, String str) {
        int count = 0;
        for(int i = 0;i<list.size();i++) {
            //获取当前元素
            String thisStr = list.get(i);
            //判断当前元素和要查找的元素是否相等
            if(thisStr.equals(str))
                count++;
        }
        return count;
    }
    //删除集合list中的所有字符串str
    private static void delAllStr(ArrayList<String> list, String str) {
        while(myContains(list, str)) {
            list.remove(str);
        }
    }

    //遍历集合中的元素
    public static void printArrayList(ArrayList<String> list) {
        System.out.print("[");
        for(int i = 0;i<list.size();i++) {
            System.out.print(list.get(i));
            if(i==list.size()-1) {
                System.out.println("]");
                return ;
            }
            System.out.print(", ");
        }
    }
    //把集合中的每个字符串中的小写字母变成大写字母
    private static void toUpperCase(ArrayList<String> list) {
        //遍历list集合
        for(int i = 0;i<list.size();i++) {
			/*//获取当前字符串
			String str = list.get(i);
			//将字符串变成字符数组
			char[] chars = str.toCharArray();
			//创建StringBuilder对象
			StringBuilder sb = new StringBuilder();
			//遍历字符数组
			for(int j = 0;j<chars.length;j++) {
				//获取当前字符
				char ch = chars[j];
				//如果是小写字母则变成大写
				if(ch>='a'&&ch<='z')
					ch-=32;
				//将大写字母添加到StringBuilder对象中
				sb.append(ch);
			}
			//将sb中的所有大写字母变成字符串，重新赋值给str，这样str中的字符串就变成了全部为大写的了
			str = sb.toString();*/
            //调用方法将集合中的当前字符串变成大写的字符串
            String str = toUpperCase(list.get(i));
            //将集合中索引为i的内容重新设置为str，但此时的str经过上面的处理全部变成了大写
            list.set(i, str);
            //String类中定义了方法toUpperCase():将字符串变成大写，这样就不用上面这么麻烦了
//			list.set(i, list.get(i).toUpperCase());
        }
    }
    //将字符串str中的小写字母变成大写字母
    private static String toUpperCase(String str) {
        //将字符串变成字符数组
        char[] chars = str.toCharArray();
        //创建StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //遍历字符数组
        for(int j = 0;j<chars.length;j++) {
            //获取当前字符
            char ch = chars[j];
            //如果是小写字母则变成大写
            if(ch>='a'&&ch<='z')
                ch-=32;
            //将大写字母添加到StringBuilder对象中
            sb.append(ch);
        }
        //将sb中的所有大写字母变成字符串，重新赋值给str，这样str中的字符串就变成了全部为大写的了
        return sb.toString();
    }
}

