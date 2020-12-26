package itheima03;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class test01 {

        public static void main(String[] args) {
            double a = divide(1, 2);
            System.out.println(a);
            int e = 1;
            int b =2;
            double c = e * 1.0 / b;
            double d = e * 1.0;
            System.out.println(c);
            System.out.println(d);
        }


        public static double divide(int a, int b) {
            return a * 1.0 / b;

    }


}
