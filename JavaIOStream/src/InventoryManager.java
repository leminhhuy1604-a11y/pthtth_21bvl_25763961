import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * InventoryManager - quản lý tồn kho bằng OOP và Java I/O streams.
 *
 * Luồng xử lý:
 * 1. Nhập danh sách sản phẩm từ bàn phím (có kiểm tra hợp lệ).
 * 2. Lưu danh sách vào data/inventory.csv (UTF-8).
 * 3. Đọc lại tệp CSV, tái tạo danh sách đối tượng Product.
 * 4. Hiển thị toàn bộ sản phẩm và tổng giá trị tồn kho.
 * 5. Tìm sản phẩm có giá trị tồn kho cao nhất.
 * 6. Ghi báo cáo tổng hợp vào data/inventory-report.txt.
 */
public class InventoryManager {

    private static final Path CSV_FILE = Path.of("data", "inventory.csv");
    private static final Path REPORT_FILE = Path.of("data", "inventory-report.txt");
    private static final String CSV_HEADER = "ma,ten,donGia,soLuong";

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        List<Product> nhapTay = nhapDanhSachSanPham(console);

        
        if (!nhapTay.isEmpty()) {
            luuVaoCsv(nhapTay, CSV_FILE);
        } else {
            System.out.println("\nKhông có sản phẩm mới nào được nhập. Giữ nguyên " + CSV_FILE);
        }
       
        System.out.println("\n===== ĐỌC LẠI DỮ LIỆU TỪ " + CSV_FILE + " =====");
        List<Product> products = docTuCsv(CSV_FILE);

        if (products == null) {
            return;
        }

        System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");
        double tongGiaTri = 0;
        for (Product p : products) {
            System.out.println(p);
            tongGiaTri += p.inventoryValue();
        }
        System.out.printf("Tổng giá trị tồn kho: %,.0f VND%n", tongGiaTri);

        // Bước 5: Tìm sản phẩm có giá trị tồn kho cao nhất
        Product caoNhat = timSanPhamCaoNhat(products);
        if (caoNhat != null) {
            System.out.println("\nSản phẩm có giá trị tồn kho cao nhất: " + caoNhat);
        }

        // Bước 6: Ghi báo cáo tổng hợp
        ghiBaoCao(products, tongGiaTri, caoNhat, REPORT_FILE);
    }

    
    private static List<Product> nhapDanhSachSanPham(BufferedReader console) {
        List<Product> danhSach = new ArrayList<>();
        System.out.println("===== NHẬP DANH SÁCH SẢN PHẨM (gõ q ở Mã sản phẩm để dừng) =====");

        while (true) {
            String code = readNonBlank(console, "Mã sản phẩm: ");
            if (code == null) {
                System.out.println("Kết thúc nhập dữ liệu (EOF).");
                break;
            }
            if (code.equalsIgnoreCase("q")) {
                break;
            }

            String name = readNonBlank(console, "Tên sản phẩm: ");
            if (name == null) {
                System.out.println("Kết thúc nhập dữ liệu (EOF).");
                break;
            }

            Double unitPrice = readPositiveDouble(console, "Đơn giá (> 0): ");
            if (unitPrice == null) {
                System.out.println("Kết thúc nhập dữ liệu (EOF).");
                break;
            }

            Integer quantity = readNonNegativeInt(console, "Số lượng (>= 0): ");
            if (quantity == null) {
                System.out.println("Kết thúc nhập dữ liệu (EOF).");
                break;
            }

            try {
                Product p = new Product(code, name, unitPrice, quantity);
                danhSach.add(p);
                System.out.println("-> Đã thêm: " + p);
            } catch (IllegalArgumentException e) {
                System.out.println("-> Dữ liệu không hợp lệ: " + e.getMessage() + ". Bỏ qua sản phẩm này.");
            }

            System.out.println();
        }
        return danhSach;
    }

    // Đọc 1 dòng không rỗng; nhập lại nếu người dùng để trống. Trả về null nếu EOF.
    private static String readNonBlank(BufferedReader console, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = readLineSafe(console);
            if (line == null) return null;
            if (line.equalsIgnoreCase("q")) return "q";
            if (!line.isBlank()) return line.trim();
            System.out.println("Giá trị không được để trống, vui lòng nhập lại.");
        }
    }

    // Đọc số thực > 0; nhập lại nếu sai định dạng hoặc <= 0. Trả về null nếu EOF.
    private static Double readPositiveDouble(BufferedReader console, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = readLineSafe(console);
            if (line == null) return null;
            try {
                double value = Double.parseDouble(line.trim());
                if (value <= 0) {
                    System.out.println("Đơn giá phải lớn hơn 0, vui lòng nhập lại.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không phải là số hợp lệ, vui lòng nhập lại.");
            }
        }
    }

    // Đọc số nguyên >= 0; nhập lại nếu sai định dạng hoặc âm. Trả về null nếu EOF.
    private static Integer readNonNegativeInt(BufferedReader console, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = readLineSafe(console);
            if (line == null) return null;
            try {
                int value = Integer.parseInt(line.trim());
                if (value < 0) {
                    System.out.println("Số lượng không được âm, vui lòng nhập lại.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không phải là số nguyên hợp lệ, vui lòng nhập lại.");
            }
        }
    }

    // Bọc readLine() để không phải try/catch IOException lặp lại nhiều nơi.
    private static String readLineSafe(BufferedReader console) {
        try {
            return console.readLine();
        } catch (IOException e) {
            System.err.println("Lỗi đọc dữ liệu từ bàn phím: " + e.getMessage());
            return null;
        }
    }

    /**
     * Ghi danh sách sản phẩm ra tệp CSV bằng UTF-8, dùng try-with-resources.
     */
    private static void luuVaoCsv(List<Product> products, Path file) {
        try {
            if (file.getParent() != null) {
                Files.createDirectories(file.getParent());
            }
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                writer.write(CSV_HEADER);
                writer.newLine();
                for (Product p : products) {
                    writer.write(p.toCsvLine());
                    writer.newLine();
                }
            }
            System.out.println("\nĐã lưu " + products.size() + " sản phẩm vào " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Không ghi được tệp " + file + ": " + e.getMessage());
        }
    }

    /**
     * Đọc danh sách sản phẩm từ tệp CSV bằng UTF-8, dùng try-with-resources.
     * - Nếu tệp không tồn tại: báo lỗi rõ ràng và trả về null (chương trình dừng).
     * - Nếu 1 dòng thiếu cột hoặc dữ liệu không hợp lệ: bỏ qua dòng đó, báo rõ số dòng lỗi,
     *   chương trình vẫn tiếp tục xử lý các dòng còn lại.
     */
    private static List<Product> docTuCsv(Path file) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            reader.readLine(); // bỏ qua dòng tiêu đề
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length != 4) {
                    System.err.println("Bỏ qua dòng " + lineNumber + " trong " + file
                            + ": thiếu cột (cần 4 cột, có " + parts.length + ").");
                    continue;
                }
                try {
                    Product p = new Product(
                            parts[0].trim(),
                            parts[1].trim(),
                            Double.parseDouble(parts[2].trim()),
                            Integer.parseInt(parts[3].trim()));
                    products.add(p);
                } catch (NumberFormatException e) {
                    System.err.println("Bỏ qua dòng " + lineNumber + " trong " + file
                            + ": dữ liệu số không hợp lệ (" + e.getMessage() + ").");
                } catch (IllegalArgumentException e) {
                    System.err.println("Bỏ qua dòng " + lineNumber + " trong " + file
                            + ": " + e.getMessage() + ".");
                }
            }
        } catch (NoSuchFileException e) {
            System.err.println("Không tìm thấy tệp: " + file.toAbsolutePath());
            return null;
        } catch (IOException e) {
            System.err.println("Không đọc được tệp " + file + ": " + e.getMessage());
            return null;
        }
        return products;
    }

    // Tìm sản phẩm có giá trị tồn kho cao nhất trong danh sách.
    private static Product timSanPhamCaoNhat(List<Product> products) {
        Product caoNhat = null;
        for (Product p : products) {
            if (caoNhat == null || p.inventoryValue() > caoNhat.inventoryValue()) {
                caoNhat = p;
            }
        }
        return caoNhat;
    }

    // Ghi báo cáo tổng hợp ra tệp văn bản UTF-8, dùng try-with-resources.
    private static void ghiBaoCao(List<Product> products, double tongGiaTri,
                                   Product caoNhat, Path file) {
        try {
            if (file.getParent() != null) {
                Files.createDirectories(file.getParent());
            }
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                writer.write("BÁO CÁO TỒN KHO");
                writer.newLine();
                writer.write("Số sản phẩm: " + products.size());
                writer.newLine();
                writer.write("Tổng giá trị tồn kho: %,.0f VND".formatted(tongGiaTri));
                writer.newLine();
                if (caoNhat != null) {
                    writer.write("Sản phẩm có giá trị tồn kho cao nhất: " + caoNhat);
                    writer.newLine();
                }
            }
            System.out.println("\nĐã ghi báo cáo vào " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Không ghi được báo cáo " + file + ": " + e.getMessage());
        }
    }
}
