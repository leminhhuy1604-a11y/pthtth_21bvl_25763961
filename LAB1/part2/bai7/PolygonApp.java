package part2.bai7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolygonApp {

    private static double inputPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                double val = Double.parseDouble(line);
                if (val > 0) return val;
                System.out.println("(!) Kích thước phải là số thực lớn hơn 0. Vui lòng nhập lại!");
            } catch (NumberFormatException e) {
                System.out.println("(!) Định dạng không hợp lệ. Vui lòng nhập một số thực!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Polygon> polygonList = new ArrayList<>();

        System.out.println("===================================================================");
        System.out.println("        CHƯƠNG TRÌNH KHỞI TẠO & TÍNH TOÁN ĐA GIÁC (POLYGON)");
        System.out.println("===================================================================");

        boolean running = true;
        while (running) {
            System.out.println("\n----------------- MENU LỰA CHỌN ĐA GIÁC -----------------");
            System.out.println("  1. Tam giác thường (Triangle)");
            System.out.println("  2. Tam giác cân (Isosceles Triangle)");
            System.out.println("  3. Tam giác đều (Equilateral Triangle)");
            System.out.println("  4. Tứ giác tổng quát (Quadrilateral)");
            System.out.println("  5. Hình chữ nhật (Rectangle)");
            System.out.println("  6. Hình vuông (Square)");
            System.out.println("  7. Ngũ giác đều (Regular Pentagon)");
            System.out.println("  8. Lục giác đều (Regular Hexagon)");
            System.out.println("  9. Bát giác đều (Regular Octagon)");
            System.out.println(" 10. Xem danh sách tất cả các hình đã tạo");
            System.out.println(" 11. Chạy demo tự động (Test tất cả các hình mẫu)");
            System.out.println("  0. Thoát chương trình");
            System.out.print("Mời bạn chọn chức năng [0 - 11]: ");

            String choice = scanner.nextLine().trim();
            Polygon poly = null;

            try {
                switch (choice) {
                    case "1": {
                        System.out.println("\n--- TẠO HÌNH TAM GIÁC THƯỜNG ---");
                        double a = inputPositiveDouble(scanner, "Nhập độ dài cạnh a: ");
                        double b = inputPositiveDouble(scanner, "Nhập độ dài cạnh b: ");
                        double c = inputPositiveDouble(scanner, "Nhập độ dài cạnh c: ");
                        poly = new Triangle(a, b, c);
                        break;
                    }
                    case "2": {
                        System.out.println("\n--- TẠO HÌNH TAM GIÁC CÂN ---");
                        double equalSide = inputPositiveDouble(scanner, "Nhập độ dài 2 cạnh bên bằng nhau: ");
                        double baseSide = inputPositiveDouble(scanner, "Nhập độ dài cạnh đáy: ");
                        poly = new IsoscelesTriangle(equalSide, baseSide);
                        break;
                    }
                    case "3": {
                        System.out.println("\n--- TẠO HÌNH TAM GIÁC ĐỀU ---");
                        double side = inputPositiveDouble(scanner, "Nhập độ dài cạnh: ");
                        poly = new EquilateralTriangle(side);
                        break;
                    }
                    case "4": {
                        System.out.println("\n--- TẠO HÌNH TỨ GIÁC TỔNG QUÁT ---");
                        double a = inputPositiveDouble(scanner, "Nhập cạnh thứ nhất: ");
                        double b = inputPositiveDouble(scanner, "Nhập cạnh thứ hai: ");
                        double c = inputPositiveDouble(scanner, "Nhập cạnh thứ ba: ");
                        double d = inputPositiveDouble(scanner, "Nhập cạnh thứ tư: ");
                        poly = new Quadrilateral(a, b, c, d);
                        break;
                    }
                    case "5": {
                        System.out.println("\n--- TẠO HÌNH CHỮ NHẬT ---");
                        double l = inputPositiveDouble(scanner, "Nhập chiều dài: ");
                        double w = inputPositiveDouble(scanner, "Nhập chiều rộng: ");
                        poly = new Rectangle(l, w);
                        break;
                    }
                    case "6": {
                        System.out.println("\n--- TẠO HÌNH VUÔNG ---");
                        double side = inputPositiveDouble(scanner, "Nhập độ dài cạnh: ");
                        poly = new Square(side);
                        break;
                    }
                    case "7": {
                        System.out.println("\n--- TẠO HÌNH NGŨ GIÁC ĐỀU ---");
                        double side = inputPositiveDouble(scanner, "Nhập độ dài cạnh ngũ giác đều: ");
                        poly = new Pentagon(side);
                        break;
                    }
                    case "8": {
                        System.out.println("\n--- TẠO HÌNH LỤC GIÁC ĐỀU ---");
                        double side = inputPositiveDouble(scanner, "Nhập độ dài cạnh lục giác đều: ");
                        poly = new Hexagon(side);
                        break;
                    }
                    case "9": {
                        System.out.println("\n--- TẠO HÌNH BÁT GIÁC ĐỀU ---");
                        double side = inputPositiveDouble(scanner, "Nhập độ dài cạnh bát giác đều: ");
                        poly = new Octagon(side);
                        break;
                    }
                    case "10": {
                        System.out.println("\n=========== DANH SÁCH CÁC ĐA GIÁC ĐÃ TẠO ===========");
                        if (polygonList.isEmpty()) {
                            System.out.println("Chưa có đa giác nào được tạo!");
                        } else {
                            for (int i = 0; i < polygonList.size(); i++) {
                                System.out.printf("[%2d] %s%n", (i + 1), polygonList.get(i));
                            }
                        }
                        break;
                    }
                    case "11": {
                        System.out.println("\n=> Đang khởi tạo bộ dữ liệu đa giác mẫu...");
                        polygonList.add(new Triangle(3, 4, 5));
                        polygonList.add(new IsoscelesTriangle(5, 6));
                        polygonList.add(new EquilateralTriangle(6));
                        polygonList.add(new Quadrilateral(4, 5, 7, 6));
                        polygonList.add(new Rectangle(8, 4));
                        polygonList.add(new Square(5));
                        polygonList.add(new Pentagon(5));
                        polygonList.add(new Hexagon(4));
                        polygonList.add(new Octagon(3));

                        System.out.println("Đã thêm 9 đa giác mẫu vào danh sách thành công!");
                        System.out.println("=========== KẾT QUẢ TÍNH TOÁN CÁC HÌNH MẪU ===========");
                        for (int i = 0; i < polygonList.size(); i++) {
                            Polygon p = polygonList.get(i);
                            System.out.printf("[%2d] %-38s | Chu vi = %7.2f | Diện tích = %7.2f%n",
                                    (i + 1), p.getName(), p.perimeter(), p.area());
                        }
                        break;
                    }
                    case "0": {
                        running = false;
                        System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                        break;
                    }
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 11.");
                }

                if (poly != null) {
                    polygonList.add(poly);
                    System.out.println("\n================ KẾT QUẢ TÍNH TOÁN ================");
                    System.out.println("Đa giác vừa tạo: " + poly.getName());
                    System.out.printf(" - Chu vi (Perimeter):  %.2f%n", poly.perimeter());
                    System.out.printf(" - Diện tích (Area):    %.2f%n", poly.area());
                    System.out.println("===================================================");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\n[LỖI] " + e.getMessage());
            }
        }

        scanner.close();
    }
}
