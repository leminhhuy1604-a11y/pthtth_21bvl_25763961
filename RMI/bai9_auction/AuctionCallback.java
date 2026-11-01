package bai9_auction;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuctionCallback extends Remote {
    void onNewBid(int productId, String productName, double newPrice, String bidderName) throws RemoteException;
    void onNewProduct(Product product) throws RemoteException;
}
