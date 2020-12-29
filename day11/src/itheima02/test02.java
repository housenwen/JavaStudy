package itheima02;

public class test02 {
    public static void main(String[] args) {
        String big = "woaiheima,woaiheimabutongyubaima,wulunheimawoaiheimahaishibaima,zhaodaogongzuojiushihaoma";
        String small = "woaiheima";


        int index = 0;
        /*
         * indexOf(String str, int fromIndex)
         * 该方法作用：从fromIndex位置开始查找，字符串str第一次出现的位置；若没找到，放回-1
         */


       int c = getCount(big, small);
        System.out.println(c);

    }

    private static int getCount(String big, String small) {
        int count = 0;
        int index;
        while ((index = big.indexOf(small)) != -1) {//说明查找到了字符串small出现的位置
//            index++;
            count++;
            big = big.substring(index + 1);//更新str字符串中的内容
        }
        return count;
    }
}
