package bai1_hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "HelloService";

    public static void main(String[] args) {
        try {
            System.out.println("[Client] Dang ket noi toi RMI Server tai " + HOST + ":" + PORT + "...");
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            HelloService service = (HelloService) registry.lookup(SERVICE_NAME);

            System.out.println("[Client] Dang goi phuong thuc sayHello()...");
            String message = service.sayHello();

            System.out.println("[Client] Ket qua nhan duoc tu Server: " + message);
        } catch (Exception e) {
            System.err.println("[Client] Loi khi ket noi hoac goi RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
