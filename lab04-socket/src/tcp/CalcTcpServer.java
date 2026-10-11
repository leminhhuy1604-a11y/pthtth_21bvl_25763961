package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CalcTcpServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Calc TCP Server listening on port " + PORT);
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

        String[] parts = trimmed.split("\\s+");
        if (parts.length != 4 || !parts[0].equalsIgnoreCase("CALC")) {
            return "ERR INVALID_FORMAT";
        }

        String op = parts[1];
        try {
            double num1 = Double.parseDouble(parts[2]);
            double num2 = Double.parseDouble(parts[3]);
            double result;

            switch (op) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> {
                    if (num2 == 0) return "ERR DIVIDE_BY_ZERO";
                    result = num1 / num2;
                }
                case "%" -> {
                    if (num2 == 0) return "ERR DIVIDE_BY_ZERO";
                    result = num1 % num2;
                }
                default -> {
                    return "ERR UNSUPPORTED_OPERATOR";
                }
            }
            return "OK " + result;
        } catch (NumberFormatException e) {
            return "ERR INVALID_NUMBER";
        }
    }
}