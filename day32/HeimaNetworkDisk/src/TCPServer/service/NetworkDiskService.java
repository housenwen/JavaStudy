package TCPServer.service;

public interface NetworkDiskService {
    /**
     * 1.读取配置文件中端口信息，初始化服务端
     * 2.线程池初始化
     */
    void init();

    /**
     * 接收客户端连接,使用线程池统一处理
     */
    void start();
}
