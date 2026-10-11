package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUdpServer {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        byte[] buffer = new byte[4096];
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("DateTime UDP Server listening on port " + PORT);
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), request.getOffset(), request.getLength(), StandardCharsets.UTF_8).trim();
                String responseText = process(message);

                byte[] responseData = responseText.getBytes(StandardCharsets.UTF_8);
                DatagramPacket response = new DatagramPacket(
                        responseData, responseData.length,
                        request.getAddress(), request.getPort());
                socket.send(response);
            }
        } catch (IOException e) {
            System.err.println("UDP server error: " + e.getMessage());
        }
    }

    static String process(String request) {
        LocalDateTime now = LocalDateTime.now();
        if (request.equalsIgnoreCase("DATE")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            return "OK " + now.format(formatter);
        } else if (request.equalsIgnoreCase("TIME")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH mm ss");
            return "OK " + now.format(formatter);
        } else if (request.equalsIgnoreCase("DATETIME")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");
            return "OK " + now.format(formatter);
        }
        return "ERR UNKNOWN_COMMAND";
    }
}