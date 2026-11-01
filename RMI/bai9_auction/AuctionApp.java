package bai9_auction;

import java.util.Scanner;

public class AuctionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("   HE THONG DAU GIA TRUC TUYEN RMI (AUCTION APP)");
        System.out.println("==================================================");
        System.out.println("Chon vai tro cua ban:");
        System.out.println("1. Chu so huu / Nguoi ban (SellerClient)");
        System.out.println("2. Nguoi tham gia dau gia (BidderClient)");
        System.out.print("Nhap lua chon (1 hoac 2): ");

        String role = scanner.nextLine().trim();
        if ("1".equals(role)) {
            SellerClient.main(args);
        } else if ("2".equals(role)) {
            BidderClient.main(args);
        } else {
            System.out.println("Lua chon khong hop le. Chuong trinh ket thuc.");
        }
    }
}
