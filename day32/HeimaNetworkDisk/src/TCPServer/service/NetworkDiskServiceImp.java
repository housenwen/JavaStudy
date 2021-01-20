package TCPServer.service;

import TCPServer.exception.BusinessException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkDiskServiceImp implements NetworkDiskService{

    private ServerSocket serverSocket;
    private ResourceBundle bundle;
    private ExecutorService threadPool;

    public NetworkDiskServiceImp() {
        init();
    }
    /**
     * 1.读取配置文件中端口信息，初始化服务端
     * 2.线程池初始化
     */
    @Override
    public void init() {
        //讀取配置文件中端口信息，初始化服務端
        bundle = ResourceBundle.getBundle("NetworkDisk");

        int prot = Integer.parseInt("8888");
        try {
            serverSocket = new ServerSocket(prot);
        }catch (IOException e){
            throw new BusinessException("創建端口失敗，檢查端口是否有衝突",e);
        }
        //綫程池初始化
        threadPool = Executors.newFixedThreadPool(50);

    }
    /**
     * 接收客户端连接,使用线程池统一处理
     */

    @Override
    public void start() {
        //接受客戶端鏈接，使用綫程池統一處理
        while (true){
            try{
                Socket socket = serverSocket.accept();
                threadPool.submit(new FileUpDownServiceImp(socket));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
