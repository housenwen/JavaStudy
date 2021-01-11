package Lambda;

public class test2 {
    public static void main(String[] args) {
// TODO 请分别使用Lambda【标准格式】及【省略格式】调用invokeCalc方法来计算130‐120的结果
        invokeCalc(130,120,(a,b)->a-b);

        invokeCalc(130,120,(int a,int b)->{
            return a-b;
        });
    }
    public static void invokeCalc(int a,int b,Calculator calculator){
        int num = calculator.calc(a,b);
        System.out.println(num);
    }
}
