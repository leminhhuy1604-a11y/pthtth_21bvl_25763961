package tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        RoomManager roomManager = new RoomManager();
        ExecutorService pool = Executors.newFixedThreadPool(20);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP Chat Server đang chạy trên cổng " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                pool.submit(new ClientHandler(socket, roomManager));
            }
        } catch (IOException e) {
            System.err.println("Lỗi Server: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}