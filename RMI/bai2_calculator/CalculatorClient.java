package bai2_calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "CalculatorService";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            CalculatorService service = (CalculatorService) registry.lookup(SERVICE_NAME);
            System.out.println("[Client] Da ket noi den CalculatorService thanh cong!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n-------------------------------------------");
                System.out.println("TINH TONG HAI SO QUA RMI (nhap -9999 de thoat)");
                System.out.print("Nhap so nguyen thu nhat a: ");
                int a = scanner.nextInt();
                if (a == -9999) break;

                System.out.print("Nhap so nguyen thu hai b: ");
                int b = scanner.nextInt();

                int result = service.add(a, b);
                System.out.printf("[Ket qua tu Server]: %d + %d = %d%n", a, b, result);
            }
            System.out.println("[Client] Ket thuc chuong trinh.");
        } catch (Exception e) {
            System.err.println("[Client] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
