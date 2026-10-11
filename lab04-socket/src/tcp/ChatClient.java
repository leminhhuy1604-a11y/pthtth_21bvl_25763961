package tcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            
            System.out.print("Nhập nickname của bạn: ");
            String nick = console.readLine();
            out.println("NICK " + nick);

            String response = in.readLine();
            System.out.println("Server: " + response);
            if (response == null || response.startsWith("ERR")) {
                System.out.println("Đăng ký nickname thất bại hoặc trùng lặp.");
                return;
            }

            System.out.println("Đã vào phòng chat! Gõ tin nhắn trực tiếp hoặc lệnh USERS, QUIT.");

            // Tạo luồng riêng để nhận tin nhắn broadcast liên tục từ server
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Đã ngắt kết nối khỏi server.");
                }
            });
            readerThread.start();

            // Luồng chính đọc bàn phím và gửi lên server
            String input;
            while ((input = console.readLine()) != null) {
                if (input.equalsIgnoreCase("QUIT")) {
                    out.println("QUIT");
                    break;
                } else if (input.equalsIgnoreCase("USERS")) {
                    out.println("USERS");
                } else {
                    out.println("MSG " + input);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}