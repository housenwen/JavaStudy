public class test04 {

//    求100以内的偶数和
    public static void main(String[] args) {

        int num = 0;
        for (int i = 0; i <= 100; i++) {


            if (i % 2 == 0) {
                num += i;

            }

        }
        System.out.println(num);
    }
}
