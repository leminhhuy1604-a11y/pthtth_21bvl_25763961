package part2.bai1;

public class HCN {
    protected double chieuDai;
    protected double chieuRong;

    // Khởi tạo không tham số
    public HCN() {
        this.chieuDai = 1.0;
        this.chieuRong = 1.0;
    }

    // Khởi tạo đầy đủ tham số
    public HCN(double chieuDai, double chieuRong) {
        if (chieuDai < 0 || chieuRong < 0) {
            throw new IllegalArgumentException("Chiều dài và chiều rộng phải lớn hơn hoặc bằng 0");
        }
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }

    // Các phương thức get
    public double getChieuDai() {
        return chieuDai;
    }

    public double getChieuRong() {
        return chieuRong;
    }

    // Các phương thức set
    public void setChieuDai(double chieuDai) {
        if (chieuDai < 0) {
            throw new IllegalArgumentException("Chiều dài phải lớn hơn hoặc bằng 0");
        }
        this.chieuDai = chieuDai;
    }

    public void setChieuRong(double chieuRong) {
        if (chieuRong < 0) {
            throw new IllegalArgumentException("Chiều rộng phải lớn hơn hoặc bằng 0");
        }
        this.chieuRong = chieuRong;
    }

    // Phương thức tính Chu vi
    public double tinhChuVi() {
        return 2 * (chieuDai + chieuRong);
    }

    // Phương thức tính Diện tích
    public double tinhDienTich() {
        return chieuDai * chieuRong;
    }

    // Phương thức xuất thông tin của đối tượng
    public void xuatThongTin() {
        System.out.printf("[Hình Chữ Nhật] Dài: %.2f | Rộng: %.2f | Chu vi: %.2f | Diện tích: %.2f%n",
                chieuDai, chieuRong, tinhChuVi(), tinhDienTich());
    }

    @Override
    public String toString() {
        return String.format("HCN[dai=%.2f, rong=%.2f, chuVi=%.2f, dienTich=%.2f]",
                chieuDai, chieuRong, tinhChuVi(), tinhDienTich());
    }
}
