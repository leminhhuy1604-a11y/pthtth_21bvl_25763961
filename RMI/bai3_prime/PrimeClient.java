package bai3_prime;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PrimeClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "PrimeService";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            PrimeService service = (PrimeService) registry.lookup(SERVICE_NAME);
            System.out.println("[Client] Da ket noi den PrimeService thanh cong!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n-------------------------------------------");
                System.out.println("KIEM TRA SO NGUYEN TO QUA RMI (nhap -1 de thoat)");
                System.out.print("Nhap mot so nguyen can kiem tra: ");
                int n = scanner.nextInt();
                if (n == -1) break;

                boolean isPrime = service.isPrime(n);
                if (isPrime) {
                    System.out.printf("[Ket qua tu Server]: %d LA SO NGUYEN TO.%n", n);
                } else {
                    System.out.printf("[Ket qua tu Server]: %d KHONG PHAI LA SO NGUYEN TO.%n", n);
                }
            }
            System.out.println("[Client] Ket thuc chuong trinh.");
        } catch (Exception e) {
            System.err.println("[Client] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
