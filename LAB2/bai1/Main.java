package bai1;

public class Main {
    public static void inTieuDeBang() {
        System.out.println("+----------+--------------------------+--------------------+----------+--------------------+");
        System.out.println("| Mã SP    | Tên sản phẩm             | Đơn giá            | Tồn kho  | Thành tiền         |");
        System.out.println("+----------+--------------------------+--------------------+----------+--------------------+");
    }

    public static void inChanBang() {
        System.out.println("+----------+--------------------------+--------------------+----------+--------------------+");
    }

    public static void inBangSanPham(SanPham... danhSach) {
        inTieuDeBang();
        for (SanPham sp : danhSach) {
            sp.hienThiThongTin();
        }
        inChanBang();
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("               BÀI 1: CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM - TÍNH ĐÓNG GÓI                     ");
        System.out.println("=========================================================================================\n");

        // 1. Tạo ít nhất hai sản phẩm sử dụng constructor đầy đủ tham số
        System.out.println("1. KHỞI TẠO HAI SẢN PHẨM BAN ĐẦU:");
        SanPham sp1 = new SanPham("SP01", "Laptop Dell XPS 15", 25000000, 10);
        SanPham sp2 = new SanPham("SP02", "Chuột Logitech MX Master", 2200000, 5);

        inBangSanPham(sp1, sp2);

        // 2. Nhập thêm hàng cho một sản phẩm
        System.out.println("\n2. THỰC HIỆN THAO TÁC NHẬP HÀNG CHO SẢN PHẨM 1 (SP01):");
        System.out.println("- Thử nhập số lượng không hợp lệ (-3):");
        sp1.nhapHang(-3);

        System.out.println("- Thử nhập thêm 15 sản phẩm:");
        sp1.nhapHang(15);

        System.out.println("\n>> Thông tin sau khi nhập hàng:");
        inBangSanPham(sp1, sp2);

        // 3. Thử bán hàng thành công
        System.out.println("\n3. THỰC HIỆN BÁN HÀNG THÀNH CÔNG VỚI SẢN PHẨM 1 (SP01):");
        System.out.println("- Bán 8 chiếc laptop Dell XPS 15:");
        boolean ketQuaBan1 = sp1.banHang(8);
        System.out.println("-> Trạng thái kết quả trả về: " + ketQuaBan1);

        System.out.println("\n>> Thông tin sau khi bán hàng thành công:");
        inBangSanPham(sp1, sp2);

        // 4. Thử bán số lượng lớn hơn tồn kho
        System.out.println("\n4. THỬ BÁN HÀNG VƯỢT QUÁ SỐ LƯỢNG TỒN KHO VỚI SẢN PHẨM 2 (SP02):");
        System.out.println("- SP02 hiện có tồn kho là: " + sp2.getSoLuong());
        System.out.println("- Thử bán 10 chiếc chuột Logitech (lớn hơn tồn kho):");
        boolean ketQuaBan2 = sp2.banHang(10);
        System.out.println("-> Trạng thái kết quả trả về: " + ketQuaBan2);

        System.out.println("\n>> Thông tin sản phẩm sau khi bán thất bại (số lượng được giữ nguyên):");
        inBangSanPham(sp1, sp2);

        // 5. Thử nghiệm thêm: Bán số lượng không hợp lệ (<= 0)
        System.out.println("\n5. KIỂM SOÁT DỮ LIỆU ĐẦU VÀO VỚI SỐ LƯỢNG BÁN KHÔNG HỢP LỆ (0 hoặc âm):");
        System.out.println("- Thử bán 0 sản phẩm SP02:");
        sp2.banHang(0);

        System.out.println("\n>> BẢNG TỔNG KẾT THÔNG TIN SẢN PHẨM CUỐI CÙNG:");
        inBangSanPham(sp1, sp2);

        System.out.println("=> KẾT LUẬN: Dữ liệu thuộc tính được bảo vệ toàn vẹn nhờ tính đóng gói!");
    }
}
