package bai9_auction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionService extends Remote {
    // 1. Chu so huu (Seller): Them san pham dau gia
    int addProduct(String sellerName, String productName, double startingPrice) throws RemoteException;

    // 2. Nguoi dau gia (Bidder): Xem danh sach san pham
    List<Product> getActiveProducts() throws RemoteException;

    // Chi tiet san pham
    Product getProductDetails(int productId) throws RemoteException;

    // 2. Nguoi dau gia (Bidder): Dua ra gia thau cao hon gia hien tai
    boolean placeBid(int productId, String bidderName, double amount) throws RemoteException;

    // 3. Server: Dang ky/Huy dang ky lang nghe cap nhat real-time
    void registerCallback(AuctionCallback callback) throws RemoteException;
    void unregisterCallback(AuctionCallback callback) throws RemoteException;
}
