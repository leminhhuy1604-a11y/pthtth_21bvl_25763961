package bai2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("       BÀI 2: CHƯƠNG TRÌNH QUẢN LÝ NGƯỜI TRONG TRƯỜNG ĐẠI HỌC - TÍNH KẾ THỪA             ");
        System.out.println("=========================================================================================\n");

        // 1. Tạo hai đối tượng sinh viên
        SinhVien sv1 = new SinhVien(
                "Nguyễn Văn An", 
                2004, 
                "Hà Nội", 
                "SV001", 
                "Công nghệ thông tin", 
                8.8
        );

        SinhVien sv2 = new SinhVien(
                "Trần Thị Mai", 
                2005, 
                "Đà Nẵng", 
                "SV002", 
                "Hệ thống thông tin", 
                7.6
        );

        // 2. Tạo hai đối tượng giảng viên
        GiangVien gv1 = new GiangVien(
                "TS. Lê Minh Huy", 
                1982, 
                "TP. Hồ Chí Minh", 
                "GV101", 
                "Trí tuệ nhân tạo & Khoa học dữ liệu", 
                12000000, 
                3.5
        );

        GiangVien gv2 = new GiangVien(
                "ThS. Phạm Hoàng Nam", 
                1988, 
                "Cần Thơ", 
                "GV102", 
                "Kỹ thuật phần mềm & Mạng máy tính", 
                10000000, 
                2.8
        );

        // 3. Hiển thị thông tin từng đối tượng (sử dụng phương thức ghi đè hienThiThongTin)
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                      DANH SÁCH THÔNG TIN CHI TIẾT TỪNG ĐỐI TƯỢNG                        ");
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("\n--- [SINH VIÊN 1] ---");
        sv1.hienThiThongTin();

        System.out.println("\n--- [SINH VIÊN 2] ---");
        sv2.hienThiThongTin();

        System.out.println("\n--- [GIẢNG VIÊN 1] ---");
        gv1.hienThiThongTin();

        System.out.println("\n--- [GIẢNG VIÊN 2] ---");
        gv2.hienThiThongTin();

        // 4. Hiển thị riêng xếp loại sinh viên theo yêu cầu
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("                       TỔNG KẾT XẾP LOẠI CỦA TỪNG SINH VIÊN                             ");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-8s | %-12s |%n", "Mã SV", "Họ và tên", "Điểm TB", "Xếp loại");
        System.out.println("+----------+----------------------+----------+--------------+");
        System.out.printf("| %-8s | %-20s | %-8.2f | %-12s |%n", 
                sv1.getMaSinhVien(), sv1.getHoTen(), sv1.getDiemTrungBinh(), sv1.xepLoai());
        System.out.printf("| %-8s | %-20s | %-8.2f | %-12s |%n", 
                sv2.getMaSinhVien(), sv2.getHoTen(), sv2.getDiemTrungBinh(), sv2.xepLoai());
        System.out.println("+----------+----------------------+----------+--------------+");

        // 5. Hiển thị riêng lương giảng viên theo yêu cầu
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.println("                      BẢNG LƯƠNG TÍNH TOÁN CỦA GIẢNG VIÊN                                ");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-22s | %-18s | %-10s | %-20s |%n", 
                "Mã GV", "Họ và tên", "Lương cơ bản", "Hệ số", "Lương thực nhận");
        System.out.println("+----------+------------------------+--------------------+------------+----------------------+");
        System.out.printf("| %-8s | %-22s | %,14.0f VNĐ | %-10.2f | %,16.0f VNĐ |%n", 
                gv1.getMaGiangVien(), gv1.getHoTen(), gv1.getLuongCoBan(), gv1.getHeSoLuong(), gv1.tinhLuong());
        System.out.printf("| %-8s | %-22s | %,14.0f VNĐ | %-10.2f | %,16.0f VNĐ |%n", 
                gv2.getMaGiangVien(), gv2.getHoTen(), gv2.getLuongCoBan(), gv2.getHeSoLuong(), gv2.tinhLuong());
        System.out.println("+----------+------------------------+--------------------+------------+----------------------+");
    }
}
