package part2.bai3_4_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChaySinhVien {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("       BÀI 5: QUẢN LÝ SINH VIÊN (STUDENT DEMO)");
        System.out.println("==========================================================");

        List<Student> danhSach = new ArrayList<>();

        System.out.print("Bạn muốn tự nhập từ bàn phím hay dùng dữ liệu mẫu có sẵn? (1: Nhập bàn phím | 2: Dữ liệu mẫu): ");
        String luaChon = scanner.nextLine().trim();

        if ("1".equals(luaChon)) {
            int n = 0;
            while (true) {
                try {
                    System.out.print("Nhập số lượng sinh viên (n > 0): ");
                    n = Integer.parseInt(scanner.nextLine().trim());
                    if (n > 0) break;
                    System.out.println("Số lượng phải lớn hơn 0!");
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập một số nguyên!");
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Nhập thông tin sinh viên thứ " + (i + 1) + " ---");
                Student sv = new Student();
                sv.inputInfo(scanner);
                danhSach.add(sv);
            }
        } else {
            System.out.println("=> Đang nạp danh sách dữ liệu mẫu...");
            danhSach.add(new Student("Nguyễn Văn An", "Nam", "12/03/2003", "Hà Nội", 8.5, "an.nv@gmail.com"));
            danhSach.add(new Student("Trần Thị Bình", "Nữ", "05/09/2003", "Đà Nẵng", 7.2, "binh.tt@yahoo.com"));
            danhSach.add(new Student("Lê Hoàng Cường", "Nam", "21/11/2002", "TP HCM", 9.3, "cuong.lh@gmail.com"));
            danhSach.add(new Student("Phạm Diệu Linh", "Nữ", "18/01/2003", "Cần Thơ", 6.8, "linh.pd@edu.vn"));
            danhSach.add(new Student("Hoàng Quốc Minh", "Nam", "30/07/2003", "Hải Phòng", 8.1, "minh.hq@gmail.com"));
        }

        // 1. Hiển thị tất cả sinh viên ra màn hình
        System.out.println("\n================ DANH SÁCH TẤT CẢ SINH VIÊN ================");
        for (int i = 0; i < danhSach.size(); i++) {
            System.out.printf("[%2d] ", (i + 1));
            danhSach.get(i).printInfo();
        }

        // 2. Tìm SV có ĐTB cao nhất và thấp nhất
        Student maxGpaSv = danhSach.get(0);
        Student minGpaSv = danhSach.get(0);

        for (Student sv : danhSach) {
            if (sv.getGpa() > maxGpaSv.getGpa()) {
                maxGpaSv = sv;
            }
            if (sv.getGpa() < minGpaSv.getGpa()) {
                minGpaSv = sv;
            }
        }

        System.out.println("\n============ SINH VIÊN CÓ ĐIỂM TRUNG BÌNH CAO NHẤT ============");
        maxGpaSv.printInfo();

        System.out.println("\n============ SINH VIÊN CÓ ĐIỂM TRUNG BÌNH THẤP NHẤT ============");
        minGpaSv.printInfo();

        // 3. Hiển thị tất cả SV được học bổng (ĐTB > 8.0)
        System.out.println("\n============ DANH SÁCH SINH VIÊN ĐƯỢC HỌC BỔNG (> 8.0) ============");
        int countHb = 0;
        for (Student sv : danhSach) {
            if (sv.isHocBong()) {
                sv.printInfo();
                countHb++;
            }
        }
        if (countHb == 0) {
            System.out.println("Không có sinh viên nào đủ điều kiện nhận học bổng.");
        } else {
            System.out.println("=> Tổng số sinh viên nhận học bổng: " + countHb + " sinh viên.");
        }

        scanner.close();
    }
}
