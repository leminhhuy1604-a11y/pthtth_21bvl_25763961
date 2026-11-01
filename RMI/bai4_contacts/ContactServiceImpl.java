package bai4_contacts;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {

    private final HashMap<String, String> contactBook = new HashMap<>();

    public ContactServiceImpl() throws RemoteException {
        super();
        // Them mot vai du lieu mau ban dau
        contactBook.put("Nguyen Van A", "0901234567");
        contactBook.put("Tran Thi B", "0912345678");
        contactBook.put("Le Van C", "0987654321");
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contactBook.put(name, phone);
        System.out.printf("[Server] Da them/cap nhat lien he: %s - %s%n", name, phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        System.out.printf("[Server] Tim kiem lien he: %s%n", name);
        return contactBook.get(name);
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        boolean removed = contactBook.remove(name) != null;
        System.out.printf("[Server] Xoa lien he '%s': %s%n", name, removed ? "Thanh cong" : "Khong tim thay");
        return removed;
    }

    @Override
    public synchronized Map<String, String> getAllContacts() throws RemoteException {
        System.out.println("[Server] Gui toan bo danh ba cho client.");
        return new HashMap<>(contactBook);
    }
}
