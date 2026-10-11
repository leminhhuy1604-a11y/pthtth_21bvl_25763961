package tcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 5000;
    private static final ConcurrentHashMap<String, PrintWriter> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Chat TCP Server listening on port " + PORT);
            while (true) {
                Socket socket = server.accept();
                pool.submit(() -> handleClient(socket));
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }

    private static void handleClient(Socket socket) {
        String nickname = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            
            // Đăng ký nickname
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("NICK ")) {
                    String name = line.substring(5).trim();
                    if (name.isEmpty() || clients.containsKey(name)) {
                        out.println("ERR NICK_TAKEN_OR_INVALID");
                    } else {
                        nickname = name;
                        clients.put(nickname, out);
                        out.println("OK WELCOME " + nickname);
                        broadcast("SERVER", nickname + " đã tham gia phòng chat.");
                        break;
                    }
                } else {
                    out.println("ERR PLEASE_REGISTER_NICK_FIRST");
                }
            }

            if (nickname == null) return;

            // Xử lý các lệnh chat
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equalsIgnoreCase("QUIT")) {
                    out.println("OK BYE");
                    break;
                } else if (line.equalsIgnoreCase("USERS")) {
                    out.println("OK USERS: " + String.join(", ", clients.keySet()));
                } else if (line.startsWith("MSG ")) {
                    String content = line.substring(4);
                    broadcast(nickname, content);
                } else {
                    out.println("ERR UNKNOWN_COMMAND");
                }
            }
        } catch (IOException e) {
            System.err.println("Mất kết nối từ client: " + e.getMessage());
        } finally {
            if (nickname != null) {
                clients.remove(nickname);
                broadcast("SERVER", nickname + " đã rời phòng chat.");
                System.out.println("Disconnected: " + nickname);
            }
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private static void broadcast(String sender, String message) {
        String formattedMsg = "[" + sender + "]: " + message;
        for (PrintWriter writer : clients.values()) {
            writer.println(formattedMsg);
        }
    }
}