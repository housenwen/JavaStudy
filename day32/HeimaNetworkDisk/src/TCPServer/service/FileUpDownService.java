package TCPServer.service;

import TCPServer.util.Protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件上传下载功能定义
 */
public interface FileUpDownService {
    void uploadFile(Protocol protocol, InputStream netIn,
                    OutputStream netOut) throws IOException;
    void downloadFile(Protocol protocol,InputStream netIn,
                      OutputStream netOut) throws IOException;
    void scanDirectory(Protocol protocol,InputStream netIn,
                       OutputStream netOut) throws IOException;
    Protocol parseProtocol(InputStream netIn) throws IOException;

}
