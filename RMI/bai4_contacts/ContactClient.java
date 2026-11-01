package bai4_contacts;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

public class ContactClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "ContactService";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            ContactService service = (ContactService) registry.lookup(SERVICE_NAME);
            System.out.println("[Client] Ket noi thanh cong toi ContactService!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n========== HE THONG QUAN LY DANH BA (RMI) ==========");
                System.out.println("1. Them/Cap nhat lien he (addContact)");
                System.out.println("2. Tim kiem so dien thoai (findContact)");
                System.out.println("3. Xoa lien he (deleteContact)");
                System.out.println("4. Xem toan bo danh ba");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) {
                    System.out.println("[Client] Tam biet!");
                    break;
                }

                switch (choice) {
                    case "1":
                        System.out.print("Nhap ten: ");
                        String nameAdd = scanner.nextLine().trim();
                        System.out.print("Nhap so dien thoai: ");
                        String phoneAdd = scanner.nextLine().trim();
                        if (nameAdd.isEmpty() || phoneAdd.isEmpty()) {
                            System.out.println("Ten va so dien thoai khong duoc de trong!");
                        } else {
                            service.addContact(nameAdd, phoneAdd);
                            System.out.println("[Client] Da luu lien he thanh cong vao server!");
                        }
                        break;

                    case "2":
                        System.out.print("Nhap ten can tim: ");
                        String nameFind = scanner.nextLine().trim();
                        String phone = service.findContact(nameFind);
                        if (phone != null) {
                            System.out.printf("[Ket qua tim kiem] %s : %s%n", nameFind, phone);
                        } else {
                            System.out.printf("[Ket qua] Khong tim thay lien he co ten: %s%n", nameFind);
                        }
                        break;

                    case "3":
                        System.out.print("Nhap ten can xoa: ");
                        String nameDel = scanner.nextLine().trim();
                        boolean deleted = service.deleteContact(nameDel);
                        if (deleted) {
                            System.out.printf("[Ket qua] Da xoa thanh cong lien he '%s'%n", nameDel);
                        } else {
                            System.out.printf("[Ket qua] Khong the xoa, khong tim thay lien he '%s'%n", nameDel);
                        }
                        break;

                    case "4":
                        Map<String, String> all = service.getAllContacts();
                        System.out.println("----- DANH SACH LIEN HE HIEN TAI (" + all.size() + ") -----");
                        if (all.isEmpty()) {
                            System.out.println("(Danh ba trong)");
                        } else {
                            for (Map.Entry<String, String> entry : all.entrySet()) {
                                System.out.printf(" - %-20s : %s%n", entry.getKey(), entry.getValue());
                            }
                        }
                        break;

                    default:
                        System.out.println("Lua chon khong hop le, vui long chon lai!");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("[Client] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
