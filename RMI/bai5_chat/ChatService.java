package bai5_chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    /**
     * Phuong thuc dung chuan yeu cau de bai Lab 06:
     * Interface co phuong thuc void sendMessage(String message).
     */
    void sendMessage(String message) throws RemoteException;

    /**
     * Dang ky callback de Server co the gui phan hoi truc tiep ve Client (Chat 1-1 2 chieu).
     */
    void registerClient(ChatCallback client) throws RemoteException;

    /**
     * Gui tin nhan kem ten nguoi gui.
     */
    void sendMessage(String sender, String message) throws RemoteException;
}
