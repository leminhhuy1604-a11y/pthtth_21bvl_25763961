package bai9_auction;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class SellerClient extends UnicastRemoteObject implements AuctionCallback {

    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "AuctionService";

    public SellerClient() throws RemoteException {
        super();
    }

    @Override
    public void onNewBid(int productId, String productName, double newPrice, String bidderName) throws RemoteException {
        System.out.printf("%n[THONG BAO THOI GIAN THUC] San pham [%d - %s] vua co gia thau moi: %,.2f VND boi '%s'!%n> ",
                productId, productName, newPrice, bidderName);
    }

    @Override
    public void onNewProduct(Product product) throws RemoteException {
        // Seller co the xem san pham moi duoc dang
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            AuctionService service = (AuctionService) registry.lookup(SERVICE_NAME);

            SellerClient callback = new SellerClient();
            service.registerCallback(callback);

            Scanner scanner = new Scanner(System.in);
            System.out.println("==================================================");
            System.out.println("   HE THONG DAU GIA TRUC TUYEN - KENH NGUOI BAN");
            System.out.println("==================================================");
            System.out.print("Nhap ten cua ban (Seller): ");
            String sellerName = scanner.nextLine().trim();
            if (sellerName.isEmpty()) sellerName = "Anonymous Seller";

            while (true) {
                System.out.println("\n---------------- MENU NGUOI BAN ----------------");
                System.out.println("1. Them san pham moi vao dau gia");
                System.out.println("2. Xem danh sach tat ca san pham & gia hien tai");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) {
                    service.unregisterCallback(callback);
                    System.out.println("[Seller] Da thoat ung dung.");
                    System.exit(0);
                    break;
                }

                switch (choice) {
                    case "1":
                        System.out.print("Nhap ten san pham: ");
                        String pName = scanner.nextLine().trim();
                        System.out.print("Nhap gia khoi diem (VND): ");
                        try {
                            double startPrice = Double.parseDouble(scanner.nextLine().trim());
                            if (startPrice <= 0) {
                                System.out.println("Gia khoi diem phai lon hon 0!");
                                break;
                            }
                            int newId = service.addProduct(sellerName, pName, startPrice);
                            System.out.printf("[Thanh cong] Da dang san pham thanh cong! Ma ID: %d%n", newId);
                        } catch (NumberFormatException e) {
                            System.out.println("Gia khoi diem khong hop le!");
                        }
                        break;

                    case "2":
                        List<Product> products = service.getActiveProducts();
                        System.out.println("----- DANH SACH SAN PHAM DANG DAU GIA -----");
                        for (Product p : products) {
                            System.out.println(p);
                        }
                        break;

                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("[Seller] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
