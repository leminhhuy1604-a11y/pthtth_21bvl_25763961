package bai5_chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

    private final Set<ChatCallback> clients = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ChatServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerClient(ChatCallback client) throws RemoteException {
        clients.add(client);
        System.out.println("[Server] Mot client da ket noi va dang ky kenh chat.");
    }

    @Override
    public synchronized void sendMessage(String message) throws RemoteException {
        String time = LocalTime.now().format(TIME_FORMAT);
        System.out.printf("[%s] [Client -> Server]: %s%n", time, message);

        // Server phan hoi lai cho tat ca client da dang ky
        String reply = "Server da nhan: \"" + message + "\" vao luc " + time;
        broadcast("Server", reply);
    }

    @Override
    public synchronized void sendMessage(String sender, String message) throws RemoteException {
        String time = LocalTime.now().format(TIME_FORMAT);
        System.out.printf("[%s] [%s]: %s%n", time, sender, message);

        String reply = "Phan hoi tu Server: Da nhan tin nhan cua " + sender + " (\"" + message + "\")";
        broadcast("Server", reply);
    }

    /**
     * Cho phep Server operator chu dong go phan hoi truc tiep tu ban phim Server gui den Client.
     */
    public synchronized void sendServerDirectMessage(String message) {
        String time = LocalTime.now().format(TIME_FORMAT);
        System.out.printf("[%s] [Server go]: %s%n", time, message);
        broadcast("Server (Admin)", message);
    }

    private void broadcast(String sender, String message) {
        for (ChatCallback client : clients) {
            try {
                client.onMessage(sender, message);
            } catch (RemoteException e) {
                System.out.println("[Server] Client da ngat ket noi.");
                clients.remove(client);
            }
        }
    }
}
