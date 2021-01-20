package TCPClient;

import java.io.*;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FileUpDownServiceImp implements FileUpDownService {
    public File current = new File("root");
    private ResourceBundle bundle;
    private String downloadPath;

    @Override
    public void start() {
        try {
            //初始化下載路徑
            bundle = ResourceBundle.getBundle("client");
            downloadPath = "d:\\heimayunpan";
            File downloadDir = new File(downloadPath);
            if (downloadDir.isFile()) {
                throw new BusinessException("文件不能當作下載目錄，請更改下載路徑配置");

            } else if (!downloadDir.exists() && !downloadDir.mkdirs()) {
                throw new BusinessException("下載目錄初始化失敗，請價差下載路徑配置是否正確");
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("*********歡迎來到黑馬網盤****************");
            ScanDirection(current);
            while (true) {
                System.out.println("********************************************");
                System.out.println("1.瀏覽當前目錄\t2.瀏覽子目錄\t3.返回上一級目錄\t4.下載文件\t5.上傳文件");
                System.out.println("********************************************");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        ScanDirection(current);
                        break;
                    case "2":
                        System.out.println("請輸入要瀏覽的子目錄");
                        String dir = sc.nextLine();
                        try {
                            //掃描
                            ScanDirection(new File(current, dir));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3":
                        if (current.getName().equals("root")) {
                            System.out.println("沒有上一級了");
                        } else {
                            ScanDirection(current.getParentFile());
                        }
                        break;
                    case "4":
                        //下載
                        System.out.println("請輸入要下載的我呢見名（含後綴）");
                        String fileName = sc.nextLine();
                        downLoadFile(new File(current, fileName));
                        break;
                    case "5":
                        while (true){
                            System.out.println("請輸入在計算機中要上傳的文件路徑");
                            String uploadFilePath = sc.nextLine();
                            File upFile = new File(uploadFilePath);
                            if (!upFile.exists()){
                                System.out.println("文件不存在，請重新輸入！");
                            }else if (upFile.isDirectory()){
                                System.out.println("抱歉不支持目錄上傳");
                            }else if (upFile.isFile()){
                                uploadFile(upFile);
                                break;
                            }
                        }
                        break;
                    default:
                        System.out.println("功能尚在開發中");

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 连接服务端
     *
     * @return
     * @throws IOException
     */

    @Override
    public Socket connect() throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        return socket;
    }
    /**
     * 文件浏览
     *
     * @param path
     * @throws IOException
     */
    @Override
    public void ScanDirection(File path) {
        try(Socket socket = connect();
            InputStream netIn = socket.getInputStream();
            OutputStream netOut = socket.getOutputStream();
        ) {
            Protocol scanDirProtocol = Protocol.getScanDirProtocol(path.getPath());
            send(netOut,scanDirProtocol);
            //接受消息
            InputStreamReader isr = new InputStreamReader(netIn);
            BufferedReader br = new BufferedReader(isr);
            String content;
            System.out.println("开始接收数据：");
            String firstLine = br.readLine();//協議
            Protocol protocol = Protocol.parseProtocol(firstLine);
            //System.err.print("pro:" + protocol);
            if (protocol.getStatus().equals(Protocol.Status.OK)){
                //成功
                current = new File(protocol.getFileName());
                System.out.println("-------------------------------------------");
                System.out.println("當前目錄："+current);
                while ((content=br.readLine())!=null){
                    System.out.println(content);
                }
            }else {
                System.out.println("瀏覽失敗"+protocol.getMessage());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void send(OutputStream netOut, Protocol scanDirProtocol) throws IOException {
        String protocol = scanDirProtocol.toString();
        //System.out.println("客户端发送：" + protocol);
        netOut.write(protocol.getBytes());

    }
    /**
     * 文件下载
     *
     * @param file
     */

    @Override
    public void downLoadFile(File file) {
        try (Socket socket = connect();
        InputStream netIn = socket.getInputStream();
        OutputStream netOut = socket.getOutputStream();){
            //下載文件
            Protocol dp = Protocol.getDownloadProtocol(file.getPath());
            send(netOut,dp);
            //接受文件
            //先讀取第一行協議
            Protocol protocol = Protocol.parseProtocol(netIn);
            if (protocol.getStatus().equals(Protocol.Status.OK)){
                System.out.println("正在下載"+protocol.getMessage());
                File local = new File(downloadPath+protocol.getFileName());
                try (FileOutputStream localOut = new FileOutputStream(local)){
                    int len;
                    byte[] bytes = new byte[1024];
                    while ((len=netIn.read(bytes))!=-1){
                        localOut.write(bytes,0,len);
                    }
                }
                System.out.println("下載成功");
            }else {
                System.out.println("下載失敗"+protocol.getMessage());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 文件上传
     *
     * @param upFile
     */
    @Override
    public void uploadFile(File upFile) {
        try(Socket socket = connect();
            InputStream netIn = socket.getInputStream();
            OutputStream netOut = socket.getOutputStream();
        ){
            //上傳文件
            Protocol up = Protocol.getUploadProtocol(new File(current,upFile.getName()));
            send(netOut,up);
            //等待響應
            Protocol protocol = Protocol.parseProtocol(netIn);
            if (protocol.getStatus().equals(Protocol.Status.OK)){
                try(FileInputStream localIn = new FileInputStream(upFile)){
                    System.out.println("開始上傳");
                    IOUtil.copy(localIn,netOut);
                    socket.shutdownOutput();
                    System.out.println("上傳結束");
                    //接收服務器響應
                    protocol = Protocol.parseProtocol(netIn);
                    if (protocol.getStatus().equals(Protocol.Status.OK)){
                        System.out.println("上傳成功");
                    }else {
                        System.out.println("上傳失敗"+protocol.getMessage());
                    }
                }
            }else {
                System.out.println("上傳失敗"+protocol.getMessage());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
