package itheima05;
//2.获取一个字符在一个字符串中出现的次数，
//
//	比如：String st = "adfdfsfksdfsdjfhsjdfhsfkdfgjdfkgljlds";
//
//	字符‘f’在字符串st中出现的次数
public class test02 {
    public static void main(String[] args) {
        String st = "adfdfsfksdfsdjfhsjdfhsfkdfgjdfkgljlds";
        char[] chars = st.toCharArray();
        int count =0;

        for (int i = 0; i <chars.length; i++) {
            if (chars[i]=='f'){
                count++;
            }
        }
        System.out.println("f出现的次数为："+count);

    }
}
