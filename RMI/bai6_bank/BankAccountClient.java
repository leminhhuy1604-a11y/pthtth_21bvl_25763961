package bai6_bank;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class BankAccountClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "BankAccountService";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            BankAccountService service = (BankAccountService) registry.lookup(SERVICE_NAME);
            System.out.println("[Client] Ket noi thanh cong toi BankAccountService!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n========== UNG DUNG NGAN HANG RMI ==========");
                System.out.println("1. Kiem tra so du (getBalance)");
                System.out.println("2. Gui tien (deposit)");
                System.out.println("3. Rut tien (withdraw)");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) {
                    System.out.println("[Client] Cam on quy khach da su dung dich vu!");
                    break;
                }

                switch (choice) {
                    case "1":
                        double balance = service.getBalance();
                        System.out.printf("[Ket qua] So du hien tai cua ban: %,.2f VND%n", balance);
                        break;

                    case "2":
                        System.out.print("Nhap so tien can gui (VND): ");
                        try {
                            double depositAmount = Double.parseDouble(scanner.nextLine().trim());
                            if (depositAmount <= 0) {
                                System.out.println("So tien gui phai lon hon 0!");
                            } else {
                                service.deposit(depositAmount);
                                System.out.printf("[Thanh cong] Da gui vao tai khoan: %,.2f VND%n", depositAmount);
                                System.out.printf("[So du moi]: %,.2f VND%n", service.getBalance());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("So tien khong hop le!");
                        } catch (Exception e) {
                            System.out.println("Loi: " + e.getMessage());
                        }
                        break;

                    case "3":
                        System.out.print("Nhap so tien can rut (VND): ");
                        try {
                            double withdrawAmount = Double.parseDouble(scanner.nextLine().trim());
                            if (withdrawAmount <= 0) {
                                System.out.println("So tien rut phai lon hon 0!");
                            } else {
                                boolean success = service.withdraw(withdrawAmount);
                                if (success) {
                                    System.out.printf("[Thanh cong] Da rut: %,.2f VND tu tai khoan.%n", withdrawAmount);
                                    System.out.printf("[So du con lai]: %,.2f VND%n", service.getBalance());
                                } else {
                                    System.out.printf("[That bai] So du khong du hoac so tien khong hop le! So du hien co: %,.2f VND%n",
                                            service.getBalance());
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("So tien khong hop le!");
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
