package bai1;

/**
 * Lớp SanPham thể hiện tính đóng gói (Encapsulation) trong OOP.
 * - Các thuộc tính được khai báo private để bảo vệ dữ liệu.
 * - Truy xuất và chỉnh sửa dữ liệu thông qua Getter/Setter và phương thức nghiệp vụ.
 * - Kiểm soát chặt chẽ tính hợp lệ của dữ liệu trước khi thay đổi trạng thái đối tượng.
 */
public class SanPham {
    // 1. Các thuộc tính private
    private String maSanPham;
    private String tenSanPham;
    private double donGia;
    private int soLuong;

    // Constructor mặc định (không tham số)
    public SanPham() {
        this.maSanPham = "";
        this.tenSanPham = "";
        this.donGia = 0.0;
        this.soLuong = 0;
    }

    // Constructor đầy đủ tham số
    public SanPham(String maSanPham, String tenSanPham, double donGia, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        setDonGia(donGia);
        setSoLuong(soLuong);
    }

    // 2. Getter và Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        if (donGia >= 0) {
            this.donGia = donGia;
        } else {
            System.out.println("[Cảnh báo] Đơn giá không được âm. Đã gán mặc định bằng 0.");
            this.donGia = 0;
        }
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong >= 0) {
            this.soLuong = soLuong;
        } else {
            System.out.println("[Cảnh báo] Số lượng không được âm. Đã gán mặc định bằng 0.");
            this.soLuong = 0;
        }
    }

    // 3. Các phương thức nghiệp vụ
    /**
     * Tính thành tiền = đơn giá * số lượng
     */
    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    /**
     * Nhập thêm hàng vào kho:
     * - Số lượng nhập phải lớn hơn 0
     * - Cộng số lượng nhập vào tồn kho
     */
    public void nhapHang(int soLuongNhap) {
        if (soLuongNhap > 0) {
            this.soLuong += soLuongNhap;
            System.out.println("-> [Thành công] Nhập thêm " + soLuongNhap + " sản phẩm [" + tenSanPham + "]. Tồn kho mới: " + this.soLuong);
        } else {
            System.out.println("-> [Thất bại] Số lượng nhập phải lớn hơn 0! (Yêu cầu nhập: " + soLuongNhap + ")");
        }
    }

    /**
     * Bán hàng:
     * - Số lượng bán phải lớn hơn 0.
     * - Không được bán quá số lượng tồn kho.
     * - Nếu bán thành công: cập nhật số lượng và trả về true.
     * - Nếu không đủ hàng: giữ nguyên số lượng và trả về false.
     */
    public boolean banHang(int soLuongBan) {
        if (soLuongBan <= 0) {
            System.out.println("-> [Thất bại] Số lượng bán phải lớn hơn 0! (Yêu cầu bán: " + soLuongBan + ")");
            return false;
        }
        if (soLuongBan > this.soLuong) {
            System.out.println("-> [Thất bại] Không đủ hàng để bán! (Yêu cầu mua: " + soLuongBan + " > Tồn kho hiện có: " + this.soLuong + ")");
            return false;
        }

        this.soLuong -= soLuongBan;
        System.out.println("-> [Thành công] Đã bán " + soLuongBan + " sản phẩm [" + tenSanPham + "]. Tồn kho còn lại: " + this.soLuong);
        return true;
    }

    /**
     * Hiển thị thông tin sản phẩm: mã, tên, đơn giá, số lượng và thành tiền.
     */
    public void hienThiThongTin() {
        System.out.printf("| %-8s | %-24s | %,14.0f VNĐ | %8d | %,16.0f VNĐ |%n",
                maSanPham, tenSanPham, donGia, soLuong, tinhThanhTien());
    }
}
