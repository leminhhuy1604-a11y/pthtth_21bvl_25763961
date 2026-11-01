package part2.bai1;

public class HVuong extends HCN {

    // Khởi tạo không tham số
    public HVuong() {
        super(1.0, 1.0);
    }

    // Khởi tạo đầy đủ tham số
    public HVuong(double canh) {
        super(canh, canh);
    }

    // Các phương thức get và set theo thuộc tính cạnh
    public double getCanh() {
        return this.chieuDai;
    }

    public void setCanh(double canh) {
        if (canh < 0) {
            throw new IllegalArgumentException("Cạnh hình vuông phải >= 0");
        }
        this.chieuDai = canh;
        this.chieuRong = canh;
    }

    @Override
    public void setChieuDai(double chieuDai) {
        setCanh(chieuDai);
    }

    @Override
    public void setChieuRong(double chieuRong) {
        setCanh(chieuRong);
    }

    // Phương thức xuất thông tin của đối tượng
    @Override
    public void xuatThongTin() {
        System.out.printf("[Hình Vuông]    Cạnh: %.2f | Chu vi: %.2f | Diện tích: %.2f%n",
                getCanh(), tinhChuVi(), tinhDienTich());
    }

    @Override
    public String toString() {
        return String.format("HVuong[canh=%.2f, chuVi=%.2f, dienTich=%.2f]",
                getCanh(), tinhChuVi(), tinhDienTich());
    }
}
