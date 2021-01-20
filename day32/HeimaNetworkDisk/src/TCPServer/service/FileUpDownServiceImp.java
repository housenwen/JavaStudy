package TCPServer.service;

import TCPServer.exception.BusinessException;
import TCPServer.util.IOUtil;
import TCPServer.util.Protocol;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

import java.io.*;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 * 协议定义:   协议+数据
 * 第一行是协议，第二行开始就是数据
 */
public class FileUpDownServiceImp implements Runnable, FileUpDownService {

    private final ResourceBundle bundle;
    private final File rootDir;
    private Socket socket;

    //todo 有參構造器 構造方法
    public FileUpDownServiceImp(Socket socket) {
        this.socket = socket;
        bundle = ResourceBundle.getBundle("NetworkDisk");
        rootDir = new File("d：\\abc");
        if (rootDir.isFile()) {
            throw new BusinessException("根目錄路徑與已存在文件衝突");
        } else if (!rootDir.exists() && !rootDir.mkdirs()) {
            throw new BusinessException("根目錄創建失敗，請檢查配置路徑是否正確");
        }
    }

    /**
     * 文件上传功能
     *
     * @param protocol
     * @param netIn
     * @param netOut
     */

    @Override
    public void uploadFile(Protocol protocol, InputStream netIn, OutputStream netOut) throws IOException {
        String fileName = protocol.getFileName().replace("root", rootDir.toString());
        File file = new File(fileName);
        if (file.exists()) {
            protocol.setStatus(Protocol.Status.FAILED);
            protocol.setMessage("文件已存在");
            send(netOut, protocol);
        } else if (file.getParentFile().exists()) {
            //告訴客戶端開始上傳
            protocol.setStatus(Protocol.Status.OK);
            send(netOut, protocol);
            try (FileOutputStream localOut = new FileOutputStream(file)) {
                System.out.println("開始上傳");
                IOUtil.copy(netIn, localOut);//拷貝
            }
            protocol.setStatus(Protocol.Status.OK);
            protocol.setMessage(file + "文件上傳成功");
            send(netOut, protocol);
        } else {
            //父級目錄不存在，不接受上傳
            protocol.setStatus(Protocol.Status.FAILED);
            protocol.setMessage("父級目錄不存在，不接受上傳");
            send(netOut, protocol);
        }
    }

    private void send(OutputStream netOut, Protocol response) throws IOException {
        String pro = response.toString();
        System.out.println("服務器響應" + pro);
        netOut.write(pro.getBytes());
    }

    /**
     * 文件下载功能
     *
     * @param protocol
     * @param netIn
     * @param netOut
     * @throws IOException
     */
    @Override
    public void downloadFile(Protocol protocol, InputStream netIn, OutputStream netOut) throws IOException {
        String fileName = protocol.getFileName().replace("root", rootDir.toString());
        File file = new File(fileName);
        Protocol response = new Protocol();
        response.setType(Protocol.Type.DOWNLOAD);
        response.setFileName(file.getName());
        if (file.isFile()) {
            response.setStatus(Protocol.Status.OK);
            response.setMessage(file.length() + "");
            send(netOut, response);
            try (FileInputStream localIn = new FileInputStream(file)) {
                IOUtil.copy(localIn, netOut);
            }
        } else {
            response.setStatus(Protocol.Status.FAILED);
            response.setMessage(file.getName() + "文件不存在，請選擇當前存在的子文件");
            send(netOut, response);
        }
    }

    /**
     * 浏览目录
     *
     * @param protocol
     * @param netIn
     * @param netOut
     * @throws IOException
     */

    @Override
    public void scanDirectory(Protocol protocol, InputStream netIn, OutputStream netOut) throws IOException {
        Protocol response = new Protocol();
        //響應客戶端使用
        //獲取客戶端想要瀏覽的目錄
        String fileName = protocol.getFileName();
        File dir = new File(fileName.replace("root", rootDir.toString()));
        if (!dir.isDirectory()) {
            //目錄不存在，構建協議返回。
            response.setType(Protocol.Type.SCAN);
            response.setStatus(Protocol.Status.FAILED);
            response.setMessage("目錄不存在，只能瀏覽當前子目錄");
            send(netOut, response);
        } else {
            response.setType(Protocol.Type.SCAN);
            response.setStatus(Protocol.Status.OK);
            response.setFileName(protocol.getFileName());
            send(netOut, response);

            //把具體數據隨後發送
            //把文件數據按照：“文件類型 名稱” 發送，每一個子文件一行
            OutputStreamWriter osw = new OutputStreamWriter(netOut);
            File[] children = dir.listFiles();

            for (File file : children) {
                String fileType = file.isFile() ? "文件" : "目錄";
                osw.write(fileType + "" + file.getName() + "\r\n");//每個文件一行

            }
            osw.flush();
        }
    }

    /**
     * 解析协议 根據輸入流解析
     *
     * @param netIn
     * @return
     * @throws IOException
     */
    @Override
    public Protocol parseProtocol(InputStream netIn) throws IOException {
        InputStreamReader isr = new InputStreamReader(netIn);
        BufferedReader br = new BufferedReader(isr);
        String protocolContent = br.readLine();
//解析協議
        Protocol protocol = Protocol.parseProtocol(protocolContent);
        return protocol;
    }

    @Override
    public void run() {
        try (
                Socket socket = this.socket;
                InputStream netIn = socket.getInputStream();
                OutputStream netOut = socket.getOutputStream();
        ) {
            //讀協議
            final Protocol protocol = parseProtocol(netIn);
            System.out.println(protocol);
            //識別客戶端操作類型
            String type = protocol.getType();
            switch (type) {
                case Protocol.Type.SCAN:
                    scanDirectory(protocol, netIn, netOut);
                    break;
                case Protocol.Type.DOWNLOAD:
                    ;
                    downloadFile(protocol, netIn, netOut);
                    break;
                case Protocol.Type.UPLOAD:
                    uploadFile(protocol, netIn, netOut);
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
