public class test13 {

//    报数游戏（1-100），遇到数字中包含 7 和 7 的倍数时需要跳过喊黑马，然后接着输出后续的数字。
//
//    分析：
//
//    A) 	1-100之间的数字可以由循环提供
//
//    B）	对循环提供的每个数字做判断， %7==0 , / 10 = 7 , %10=7时为0的则是满足的。
//
//    此时不需要打印数字，需要跳过。使用continue关键字完成

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {

            if ( i%7==0 || i/10== 7||i%10==7){

                System.out.println("黑马"+i);
                continue;
            }
            System.out.println(i);
        }
    }
}
