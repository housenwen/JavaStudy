package TCPServer.util;

import TCPServer.service.NetworkDiskService;
import TCPServer.service.NetworkDiskServiceImp;


public class ServerApp {
    public static void main(String[] args) {
        NetworkDiskService networkDisk = new NetworkDiskServiceImp();
        networkDisk.start();
    }
}
