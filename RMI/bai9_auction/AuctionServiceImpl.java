package bai9_auction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService {

    private final Map<Integer, Product> products = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(100);
    private final Set<AuctionCallback> callbacks = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public AuctionServiceImpl() throws RemoteException {
        super();
        // Khoi tao mot so san pham mau san co
        Product p1 = new Product(idCounter.incrementAndGet(), "iPhone 15 Pro Max 256GB", 25000000.0, "Shop Apple VN");
        Product p2 = new Product(idCounter.incrementAndGet(), "MacBook Pro M3 14-inch", 38000000.0, "TechZone");
        Product p3 = new Product(idCounter.incrementAndGet(), "Dong ho Rolex Submariner", 150000000.0, "Luxury Collector");

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        products.put(p3.getId(), p3);
    }

    @Override
    public synchronized int addProduct(String sellerName, String productName, double startingPrice) throws RemoteException {
        if (startingPrice <= 0) {
            throw new IllegalArgumentException("Gia khoi diem phai lon hon 0!");
        }
        int newId = idCounter.incrementAndGet();
        Product product = new Product(newId, productName, startingPrice, sellerName);
        products.put(newId, product);

        System.out.printf("[Server] Nguoi ban '%s' da them san pham: ID=%d, Ten='%s', Gia khoi diem=%,.2f VND%n",
                sellerName, newId, productName, startingPrice);

        notifyNewProduct(product);
        return newId;
    }

    @Override
    public synchronized List<Product> getActiveProducts() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    @Override
    public synchronized Product getProductDetails(int productId) throws RemoteException {
        return products.get(productId);
    }

    @Override
    public synchronized boolean placeBid(int productId, String bidderName, double amount) throws RemoteException {
        Product product = products.get(productId);
        if (product == null) {
            System.out.printf("[Server] Dat gia that bai: Khong tim thay san pham ID=%d%n", productId);
            return false;
        }

        boolean success = product.placeBid(bidderName, amount);
        if (success) {
            System.out.printf("[Server] >> GIA THAU MOI CAO NHAT! San pham: [%d] %s, Gia: %,.2f VND boi '%s'%n",
                    productId, product.getName(), amount, bidderName);
            notifyNewBid(productId, product.getName(), amount, bidderName);
            return true;
        } else {
            System.out.printf("[Server] Dat gia khong hop le cho san pham ID=%d: Yeu cau %,.2f <= Gia hien tai %,.2f%n",
                    productId, amount, product.getCurrentPrice());
            return false;
        }
    }

    @Override
    public synchronized void registerCallback(AuctionCallback callback) throws RemoteException {
        callbacks.add(callback);
        System.out.println("[Server] Mot client da dang ky nhan thong bao dau gia.");
    }

    @Override
    public synchronized void unregisterCallback(AuctionCallback callback) throws RemoteException {
        callbacks.remove(callback);
        System.out.println("[Server] Mot client da huy dang ky thong bao.");
    }

    private void notifyNewBid(int productId, String productName, double newPrice, String bidderName) {
        List<AuctionCallback> toRemove = new ArrayList<>();
        for (AuctionCallback cb : callbacks) {
            try {
                cb.onNewBid(productId, productName, newPrice, bidderName);
            } catch (RemoteException e) {
                toRemove.add(cb);
            }
        }
        callbacks.removeAll(toRemove);
    }

    private void notifyNewProduct(Product product) {
        List<AuctionCallback> toRemove = new ArrayList<>();
        for (AuctionCallback cb : callbacks) {
            try {
                cb.onNewProduct(product);
            } catch (RemoteException e) {
                toRemove.add(cb);
            }
        }
        callbacks.removeAll(toRemove);
    }
}
