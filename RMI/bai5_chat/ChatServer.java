package bai5_chat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatServer {
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "ChatService";

    public static void main(String[] args) {
        try {
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(PORT);
                System.out.println("[Server] RMI Registry da duoc khoi tao tai cong " + PORT);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(PORT);
                System.out.println("[Server] Su dung RMI Registry hien co tai cong " + PORT);
            }

            ChatServiceImpl service = new ChatServiceImpl();
            registry.rebind(SERVICE_NAME, service);

            System.out.println("[Server] ChatServer da san sang!");
            System.out.println("[Server] Ban co the go tin nhan tai console nay de phan hoi truc tiep cho Client:");

            // Thread doc ban phim tu phia Server de chat lai voi Client
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        service.sendServerDirectMessage(line);
                    }
                }
            }).start();

        } catch (Exception e) {
            System.err.println("[Server] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
