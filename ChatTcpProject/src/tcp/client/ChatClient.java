package tcp.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            
            System.out.print("Nhập tên của bạn: ");
            String name = console.readLine();
            out.println("NAME " + name);

            String response = in.readLine();
            if (response == null || !response.startsWith("OK")) {
                System.out.println("Đăng ký tên thất bại.");
                return;
            }

            while (true) {
                String serverMsg = in.readLine();
                if (serverMsg == null) break;

                if (serverMsg.startsWith("ROOM_LIST ")) {
                    String[] rooms = serverMsg.substring(10).split(",");
                    System.out.println("\n=== DANH SÁCH PHÒNG CHAT ===");
                    for (String r : rooms) {
                        System.out.println("- " + r);
                    }
                    System.out.print("Nhập tên phòng muốn vào (hoặc /exit để thoát): ");
                    String roomChoice = console.readLine();
                    out.println(roomChoice);
                    if (roomChoice != null && roomChoice.trim().equalsIgnoreCase("/exit")) {
                        break;
                    }
                } else if (serverMsg.startsWith("JOINED ")) {
                    String roomName = serverMsg.substring(7);
                    System.out.println("Đã vào phòng [" + roomName + "]. Gõ tin nhắn, /quit để về danh sách phòng, /exit để thoát.");

                    Thread roomListener = new Thread(new ClientReceiver(in));
                    roomListener.start();

                    while (true) {
                        String input = console.readLine();
                        if (input == null) break;
                        out.println(input);
                        if (input.equalsIgnoreCase("/quit") || input.equalsIgnoreCase("/exit")) {
                            break;
                        }
                    }
                    try { roomListener.join(); } catch (InterruptedException ignored) {}
                } else if (serverMsg.equals("BYE")) {
                    System.out.println("Đã ngắt kết nối khỏi server.");
                    break;
                } else if (serverMsg.startsWith("ERR")) {
                    System.out.println("Lỗi: " + serverMsg);
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}