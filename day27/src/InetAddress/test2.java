package InetAddress;

import java.io.IOException;
import java.net.InetAddress;

public class test2 {
    public static void main(String[] args) throws IOException {
        //1. 获取本机IP地址对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost="+localHost);//localHost=LAPTOP-R9VGTB0A/192.168.43.89

        //获取主机名
        String nameHost = localHost.getHostName();
        System.out.println("nameHost="+nameHost);

        //获取IP地址
        String hostAddress = localHost.getHostAddress();
        System.out.println("hostAddress="+hostAddress);

        //2. 根据IP地址字符串或主机名获得对应的IP地址对象

        InetAddress ip2 = InetAddress.getByName("LAPTOP-R9VGTB0A");
        System.out.println("ip2="+ip2);

        InetAddress ip3 = InetAddress.getByName("192.168.43.89");
        System.out.println("ip3="+ip3);
        //3. 根据网址获取IP地址对象
        InetAddress ip4 = InetAddress.getByName("www.baidu.com");
        System.out.println("ip4="+ip4);







    }
}
