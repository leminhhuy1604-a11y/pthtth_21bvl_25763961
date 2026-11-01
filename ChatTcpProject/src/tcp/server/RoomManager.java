package tcp.server;

import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RoomManager {
    public static final Set<String> ROOMS = Set.of("Study", "Game", "TraiNghiem");
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, PrintWriter>> roomClients = new ConcurrentHashMap<>();

    public RoomManager() {
        for (String room : ROOMS) {
            roomClients.put(room, new ConcurrentHashMap<>());
        }
    }

    public boolean hasRoom(String room) {
        return roomClients.containsKey(room);
    }

    public void addClient(String room, String nickname, PrintWriter out) {
        roomClients.get(room).put(nickname, out);
    }

    public void removeClient(String room, String nickname) {
        ConcurrentHashMap<String, PrintWriter> clients = roomClients.get(room);
        if (clients != null) {
            clients.remove(nickname);
        }
    }

    public void broadcast(String room, String sender, String message) {
        ConcurrentHashMap<String, PrintWriter> clients = roomClients.get(room);
        if (clients != null) {
            String formatted = "[" + room + "] " + (sender.equals("SERVER") ? message : ("[" + sender + "]: " + message));
            for (PrintWriter writer : clients.values()) {
                writer.println(formatted);
            }
        }
    }
}