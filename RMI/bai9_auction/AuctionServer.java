package bai9_auction;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuctionServer {
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "AuctionService";

    public static void main(String[] args) {
        try {
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(PORT);
                System.out.println("[Server] RMI Registry da duoc khoi tao tai cong " + PORT);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(PORT);
                System.out.println("[Server] Su dung RMI Registry hien co tai cong " + PORT);
            }

            AuctionService service = new AuctionServiceImpl();
            registry.rebind(SERVICE_NAME, service);

            System.out.println("[Server] AuctionServer (He thong Dau gia Truc tuyen) da san sang (" + SERVICE_NAME + ")...");
        } catch (Exception e) {
            System.err.println("[Server] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
