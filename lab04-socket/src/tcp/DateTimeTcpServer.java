package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTcpServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("DateTime TCP Server listening on port " + PORT);
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
        LocalDateTime now = LocalDateTime.now();
        
        if (trimmed.equalsIgnoreCase("DATE")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "OK " + now.format(formatter);
        } else if (trimmed.equalsIgnoreCase("TIME")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH mm ss");
            return "OK " + now.format(formatter);
        } else if (trimmed.equalsIgnoreCase("DATETIME")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");
            return "OK " + now.format(formatter);
        } else if (trimmed.equalsIgnoreCase("QUIT")) {
            return "OK BYE";
        }
        return "ERR UNKNOWN_COMMAND";
    }
}