package tcp.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final RoomManager roomManager;

    public ClientHandler(Socket socket, RoomManager roomManager) {
        this.socket = socket;
        this.roomManager = roomManager;
    }

    @Override
    public void run() {
        String nickname = null;
        String currentRoom = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("NAME ")) {
                    nickname = line.substring(5).trim();
                    if (nickname.isEmpty()) {
                        out.println("ERR INVALID_NAME");
                    } else {
                        out.println("OK WELCOME " + nickname);
                        break;
                    }
                } else {
                    out.println("ERR PLEASE_SEND_NAME");
                }
            }

            if (nickname == null) return;

            while (true) {
                out.println("ROOM_LIST " + String.join(",", RoomManager.ROOMS));
                
                String roomChoice = in.readLine();
                if (roomChoice == null) break;
                roomChoice = roomChoice.trim();

                if (roomChoice.equalsIgnoreCase("/exit")) {
                    out.println("BYE");
                    break;
                }

                if (roomManager.hasRoom(roomChoice)) {
                    currentRoom = roomChoice;
                    roomManager.addClient(currentRoom, nickname, out);
                    out.println("JOINED " + currentRoom);
                    roomManager.broadcast(currentRoom, "SERVER", nickname + " đã tham gia phòng.");

                    while ((line = in.readLine()) != null) {
                        line = line.trim();
                        if (line.equalsIgnoreCase("/exit")) {
                            roomManager.removeClient(currentRoom, nickname);
                            roomManager.broadcast(currentRoom, "SERVER", nickname + " đã rời phòng.");
                            out.println("BYE");
                            return;
                        } else if (line.equalsIgnoreCase("/quit")) {
                            roomManager.removeClient(currentRoom, nickname);
                            roomManager.broadcast(currentRoom, "SERVER", nickname + " đã rời phòng.");
                            out.println("LEFT_ROOM");
                            currentRoom = null;
                            break; 
                        } else {
                            roomManager.broadcast(currentRoom, nickname, line);
                        }
                    }
                } else {
                    out.println("ERR ROOM_NOT_FOUND");
                }
            }
        } catch (IOException e) {
            System.err.println("Mất kết nối client.");
        } finally {
            if (currentRoom != null && nickname != null) {
                roomManager.removeClient(currentRoom, nickname);
                roomManager.broadcast(currentRoom, "SERVER", nickname + " đã ngắt kết nối.");
            }
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}