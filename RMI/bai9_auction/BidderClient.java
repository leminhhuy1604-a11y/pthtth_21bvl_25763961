package bai9_auction;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class BidderClient extends UnicastRemoteObject implements AuctionCallback {

    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "AuctionService";

    public BidderClient() throws RemoteException {
        super();
    }

    @Override
    public void onNewBid(int productId, String productName, double newPrice, String bidderName) throws RemoteException {
        System.out.printf("%n[THONG BAO REAL-TIME] Co nguoi vua tra gia moi cho san pham [%d - %s]: %,.2f VND (Boi: %s)%n> ",
                productId, productName, newPrice, bidderName);
    }

    @Override
    public void onNewProduct(Product product) throws RemoteException {
        System.out.printf("%n[THONG BAO REAL-TIME] San pham moi vua len san: [%d] %s voi gia khoi diem %,.2f VND (Nguoi ban: %s)%n> ",
                product.getId(), product.getName(), product.getStartingPrice(), product.getSellerName());
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            AuctionService service = (AuctionService) registry.lookup(SERVICE_NAME);

            BidderClient callback = new BidderClient();
            service.registerCallback(callback);

            Scanner scanner = new Scanner(System.in);
            System.out.println("==================================================");
            System.out.println("   HE THONG DAU GIA TRUC TUYEN - KENH NGUOI DAU GIA");
            System.out.println("==================================================");
            System.out.print("Nhap ten cua ban (Bidder): ");
            String bidderName = scanner.nextLine().trim();
            if (bidderName.isEmpty()) bidderName = "Anonymous Bidder";

            while (true) {
                System.out.println("\n---------------- MENU DAU GIA ----------------");
                System.out.println("1. Xem danh sach san pham dang dau gia");
                System.out.println("2. Dat gia thau (Bid) cho san pham");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) {
                    service.unregisterCallback(callback);
                    System.out.println("[Bidder] Da thoat ung dung.");
                    System.exit(0);
                    break;
                }

                switch (choice) {
                    case "1":
                        List<Product> products = service.getActiveProducts();
                        System.out.println("----- DANH SACH SAN PHAM HIEN CO -----");
                        for (Product p : products) {
                            System.out.println(p);
                        }
                        break;

                    case "2":
                        System.out.print("Nhap ID san pham ban muon tra gia: ");
                        try {
                            int pId = Integer.parseInt(scanner.nextLine().trim());
                            Product target = service.getProductDetails(pId);
                            if (target == null) {
                                System.out.println("Khong tim thay san pham voi ID: " + pId);
                                break;
                            }

                            System.out.printf("San pham: %s | Gia hien tai: %,.2f VND (Nguoi tra cao nhat: %s)%n",
                                    target.getName(), target.getCurrentPrice(), target.getHighestBidder());
                            System.out.print("Nhap muc gia ban muon tra (phai lon hon gia hien tai): ");
                            double bidPrice = Double.parseDouble(scanner.nextLine().trim());

                            boolean success = service.placeBid(pId, bidderName, bidPrice);
                            if (success) {
                                System.out.printf("[Thanh cong] Ban da dat gia %,.2f VND cho san pham [%d - %s]!%n",
                                        bidPrice, pId, target.getName());
                            } else {
                                System.out.printf("[That bai] Muc gia cua ban (%,.2f VND) khong hop le hoac khong cao hon gia hien tai!%n",
                                        bidPrice);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Gia tri nhap vao phai la so hop le!");
                        }
                        break;

                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("[Bidder] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
