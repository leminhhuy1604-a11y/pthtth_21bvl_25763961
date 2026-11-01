package bai7_geometry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GeometryClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "GeometryService";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            GeometryService service = (GeometryService) registry.lookup(SERVICE_NAME);
            System.out.println("[Client] Ket noi thanh cong toi GeometryService!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n========== TINH DIEN TICH HINH HOC QUA RMI ==========");
                System.out.println("1. Tinh dien tich Hinh chu nhat (rectangleArea)");
                System.out.println("2. Tinh dien tich Hinh tron (circleArea)");
                System.out.println("3. Tinh dien tich Hinh tam giac (triangleArea)");
                System.out.println("0. Thoat");
                System.out.print("Lua chon cua ban: ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("0")) {
                    System.out.println("[Client] Ket thuc chuong trinh.");
                    break;
                }

                try {
                    switch (choice) {
                        case "1":
                            System.out.print("Nhap chieu rong (width): ");
                            double width = Double.parseDouble(scanner.nextLine().trim());
                            System.out.print("Nhap chieu cao (height): ");
                            double height = Double.parseDouble(scanner.nextLine().trim());
                            double rectArea = service.rectangleArea(width, height);
                            System.out.printf("[Ket qua tu Server]: Dien tich Hinh chu nhat = %.4f%n", rectArea);
                            break;

                        case "2":
                            System.out.print("Nhap ban kinh (radius): ");
                            double radius = Double.parseDouble(scanner.nextLine().trim());
                            double cArea = service.circleArea(radius);
                            System.out.printf("[Ket qua tu Server]: Dien tich Hinh tron = %.4f%n", cArea);
                            break;

                        case "3":
                            System.out.print("Nhap do dai canh day (base): ");
                            double base = Double.parseDouble(scanner.nextLine().trim());
                            System.out.print("Nhap chieu cao (height): ");
                            double triHeight = Double.parseDouble(scanner.nextLine().trim());
                            double triArea = service.triangleArea(base, triHeight);
                            System.out.printf("[Ket qua tu Server]: Dien tich Hinh tam giac = %.4f%n", triArea);
                            break;

                        default:
                            System.out.println("Lua chon khong hop le, vui long chon lai!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Gia tri nhap vao phai la so thuc!");
                } catch (Exception e) {
                    System.out.println("Loi khi tinh toan: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("[Client] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
