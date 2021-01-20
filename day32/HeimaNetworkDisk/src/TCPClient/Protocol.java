package TCPClient;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 协议定义：
 */
public class Protocol {
    //协议数据
    private String type;//操作类型
    private String fileName;//操作文件
    private String status;//操作状态
    private String message;//说明信息

    /**
     * 操作类型
     */
    public static class Type {
        public static final String SCAN = "scan";//浏览
        public static final String UPLOAD = "upload";//浏览
        public static final String DOWNLOAD = "download";//浏览
    }

    /**
     * 操作状态
     */
    public static class Status {
        public static final String OK = "ok";//成功
        public static final String FAILED = "failed";//失败
    }

    public Protocol() {
    }

    /**
     * 获取浏览目录协议
     *
     * @param path
     * @return
     */
    public static Protocol getScanDirProtocol(String path) {
        Protocol protocol = new Protocol();
        protocol.setType(Type.SCAN);
        protocol.setFileName(path);
        return protocol;
    }

    /**
     * 获取文件下载协议
     *
     * @param path
     * @return
     */
    public static Protocol getDownloadProtocol(String path) {
        Protocol protocol = new Protocol();
        protocol.setType(Type.DOWNLOAD);
        protocol.setFileName(path);
        return protocol;
    }

    /**
     * 获取文件上传协议
     *
     * @param file
     * @return
     */
    public static Protocol getUploadProtocol(File file) {
        Protocol protocol = new Protocol();
        protocol.setType(Type.UPLOAD);
        protocol.setFileName(file.getPath());

        return protocol;
    }

    //type=下载 fileName=aa.txt status=OK

    /**
     * 解析协议
     *
     * @param str
     * @return
     */
    public static Protocol parseProtocol(String str) {
        Map<String, String> proData = new HashMap<>();
        String[] data = str.split(",");
        for (String datum : data) {
            String[] strs = datum.split("[=:]", 2);
            proData.put(strs[0], strs[1]);
        }

        //通过反射进行数据填充
        Protocol protocol = new Protocol();
        Field[] declaredFields = protocol.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                field.set(protocol, proData.get(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return protocol;
    }

    public static Protocol parseProtocol(InputStream netIn) throws IOException {
        InputStreamReader isr = new InputStreamReader(netIn);
        BufferedReader br = new BufferedReader(isr);
        String protocolStr = br.readLine();

        Protocol protocol = parseProtocol(protocolStr);
        return protocol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        Field[] fields = getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                sb.append(field.getName()).append("=").append(field.get(this)).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString() + "\r\n";
    }

}
