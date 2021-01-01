public class test02 {
    public static void main(String[] args) {
//        2、输出100以内被6整除的数字，每5个换一行，

        int c =0;
        for (int i = 0; i < 100; i++) {

            if (i%6==0){

                    System.out.print(i+" ");

                    c++;

                    if (c%5==0){

                        System.out.println();
                    }

            }

        }
    }
}
