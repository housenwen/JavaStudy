package TCPClient;

public class NetworkDiskApp {
    public static void main(String[] args) {
        FileUpDownService service = new FileUpDownServiceImp();
        service.start();
    }
}

