public class test {

//    计算一张纸对叠叠成珠峰的次数
//    纸0.1毫米;珠峰8884430;
    public static void main(String[] args) {
        int count = 0;
        double paper = 0.1;
        double zf = 8884430;
        while (paper <= zf) {
            paper *= 2;
//            System.out.println(paper);
            count++;

        }
        System.out.println(count);
    }
}
