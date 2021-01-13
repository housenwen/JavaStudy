package InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class test1 {
    public static void main(String[] args) throws UnknownHostException,Exception {
        //InetAddress

        //1. 获取本机IP地址对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);//lyc-surfacebook/192.168.147.72
        //获取主机名
        String hostName = localHost.getHostName();
        System.out.println("hostName = " + hostName);//lyc-surfacebook
        //获取IP地址
        String hostAddress = localHost.getHostAddress();
        System.out.println("hostAddress = " + hostAddress);//192.168.147.72


        //2. 根据IP地址字符串或主机名获得对应的IP地址对象
        InetAddress ip2 = InetAddress.getByName("LAPTOP-R9VGTB0A");
        System.out.println("ip2 = " + ip2);//lyc-surfacebook/192.168.147.72

        InetAddress ip3 = InetAddress.getByName("192.168.147.72");
        System.out.println("ip3 = " + ip3);

        //3. 根据网址获取IP地址对象
        InetAddress ip4 = InetAddress.getByName("www.baidu.com");
        System.out.println("ip4 = " + ip4);//www.baidu.com/112.80.248.76
    }
}
