package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DigitTcpServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Digit TCP server listening on port " + PORT);
            while (true) {
                try (Socket socket = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
                    
                    String request;
                    while ((request = in.readLine()) != null) {
                        String response = process(request);
                        out.println(response);
                        if (request.trim().equalsIgnoreCase("QUIT")) break;
                    }
                } catch (IOException e) {
                    System.err.println("Lỗi phiên client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Không mở được server: " + e.getMessage());
        }
    }

    static String process(String request) {
        String trimmed = request.trim();
        if (trimmed.equalsIgnoreCase("QUIT")) return "OK BYE";

        // Kiểm tra đúng một chữ số từ 0 đến 9
        if (trimmed.length() == 1 && Character.isDigit(trimmed.charAt(0))) {
            char digit = trimmed.charAt(0);
            return switch (digit) {
                case '0' -> "OK Không";
                case '1' -> "OK Một";
                case '2' -> "OK Hai";
                case '3' -> "OK Ba";
                case '4' -> "OK Bốn";
                case '5' -> "OK Năm";
                case '6' -> "OK Sáu";
                case '7' -> "OK Bảy";
                case '8' -> "OK Tám";
                case '9' -> "OK Chín";
                default -> "ERR INVALID_DIGIT";
            };
        }
        return "ERR INVALID_DIGIT";
    }
}