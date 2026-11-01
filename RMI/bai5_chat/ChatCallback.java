package bai5_chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatCallback extends Remote {
    void onMessage(String sender, String message) throws RemoteException;
}
