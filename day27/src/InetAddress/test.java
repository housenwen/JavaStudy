package InetAddress;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public class test {
    public static void main(String[] args) throws Exception{

        InetAddress inet = InetAddress.getLocalHost();
        System.out.println(inet);

        Inet4Address inet4 = (Inet4Address) InetAddress.getLocalHost();
        System.out.println(inet4);

        InetAddress inet2 = InetAddress.getByName("192.168.43.89");
        System.out.println(inet2);

        InetAddress ine3 = InetAddress.getByName("baidu.com");
        System.out.println(ine3);

        String name = inet.getHostName();
        System.out.println(name);

        String hostAddress = inet.getHostAddress();
        System.out.println(hostAddress);

    }

}
