package TCPClient;

import java.io.File;
import java.net.Socket;

public interface FileUpDownService {
    void start();
    Socket connect() throws Exception;
    void ScanDirection(File file);
    void downLoadFile(File file);
    void uploadFile(File file);

}
