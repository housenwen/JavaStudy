package ClassWork;

public class test2_33 {
    public static void main(String[] args){
        //测试调用：会导致方法内抛出“空指针异常”
        int n1 = getMax(null);
        //测试调用：会导致方法内抛出“数组索引越界异常”
        int[] arr = new int[0];
        int n2 = getMax(arr);
        //测试调用：正常
        int[] arr2 = {1,2,4,24,32,5324,32};
        int n3 = getMax(arr2);

        System.out.println("程序继续执行");
    }
    //获取数组最大值
    public static int getMax(int[] args){
        try{
            int max = args[0];
            for(int i = 1 ; i < args.length ; i++){
                if(max < args[i]){
                    max = args[i];
                }
            }
            return max;
        } catch (NullPointerException |ArrayIndexOutOfBoundsException e) {
            System.out.println("程序编译错误原因："+e);
            return -1;
        }
    }
}
