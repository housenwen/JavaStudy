package itheima02;

public class test03 {
    public static void main(String[] args) {
        String s1 = "java woaijava,i like jajavava i enjoy java";
        String s2 = "java";

       int count =  getCount(s1,s2);

        System.out.println(count);

        String s = remove(s1,s2);

        System.out.println(s);

    }

    private static String remove(String s1, String s2) {

//删除后的结果
        String resultStr = s1.replace(s2, "");

        return resultStr;
    }

    private static int getCount(String s1, String s2) {

        int count =0;
        int index =0;

        while ((index=s1.indexOf(s2))!=-1){
            count++;
            s1 = s1.substring(index+1);
        }
        return count;
    }

}
