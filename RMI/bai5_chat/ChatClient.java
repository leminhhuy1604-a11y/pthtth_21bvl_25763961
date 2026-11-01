package bai5_chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatCallback {

    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "ChatService";

    public ChatClient() throws RemoteException {
        super();
    }

    @Override
    public void onMessage(String sender, String message) throws RemoteException {
        System.out.printf("%n<<< [%s]: %s%n> ", sender, message);
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            ChatService service = (ChatService) registry.lookup(SERVICE_NAME);

            ChatClient clientCallback = new ChatClient();
            service.registerClient(clientCallback);

            System.out.println("=================================================");
            System.out.println("   UNG DUNG CHAT 1-1 QUA RMI (CLIENT)");
            System.out.println("   (Nhap 'exit' de ket thuc)");
            System.out.println("=================================================");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap ten cua ban (biet danh): ");
            String clientName = scanner.nextLine().trim();
            if (clientName.isEmpty()) clientName = "Client";

            System.out.println("Bat dau tro chuyen. Hay nhap tin nhan:");
            System.out.print("> ");

            while (true) {
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    System.out.println("[Client] Da thoat khoi phong chat.");
                    System.exit(0);
                    break;
                }
                if (!line.isEmpty()) {
                    // Goi phuong thuc sendMessage theo dung yeu cau de bai
                    service.sendMessage("[" + clientName + "] " + line);
                }
                System.out.print("> ");
            }

        } catch (Exception e) {
            System.err.println("[Client] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
