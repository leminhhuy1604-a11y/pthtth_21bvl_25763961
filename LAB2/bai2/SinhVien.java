package bai2;

/**
 * Lớp SinhVien kế thừa từ lớp Nguoi.
 * Thể hiện tính kế thừa (Inheritance):
 * - Kế thừa các thuộc tính và phương thức từ lớp cha Nguoi.
 * - Sử dụng từ khóa 'super' để gọi constructor và phương thức của lớp cha.
 * - Ghi đè phương thức hienThiThongTin() và bổ sung các phương thức nghiệp vụ riêng.
 */
public class SinhVien extends Nguoi {
    // Thuộc tính bổ sung của SinhVien
    private String maSinhVien;
    private String nganhHoc;
    private double diemTrungBinh;

    // Constructor mặc định
    public SinhVien() {
        super();
        this.maSinhVien = "";
        this.nganhHoc = "";
        this.diemTrungBinh = 0.0;
    }

    // Constructor đầy đủ tham số sử dụng super(...)
    public SinhVien(String hoTen, int namSinh, String diaChi, 
                    String maSinhVien, String nganhHoc, double diemTrungBinh) {
        super(hoTen, namSinh, diaChi); // Gọi constructor của lớp cha Nguoi
        this.maSinhVien = maSinhVien;
        this.nganhHoc = nganhHoc;
        this.diemTrungBinh = diemTrungBinh;
    }

    // Getter và Setter cho các thuộc tính riêng
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        if (diemTrungBinh >= 0.0 && diemTrungBinh <= 10.0) {
            this.diemTrungBinh = diemTrungBinh;
        } else {
            System.out.println("[Cảnh báo] Điểm trung bình phải từ 0.0 đến 10.0!");
        }
    }

    /**
     * Phương thức xếp loại học tập dựa trên điểm trung bình:
     * - Từ 8.5 trở lên: Giỏi
     * - Từ 7.0 đến dưới 8.5: Khá
     * - Từ 5.0 đến dưới 7.0: Trung bình
     * - Dưới 5.0: Yếu
     */
    public String xepLoai() {
        if (diemTrungBinh >= 8.5) {
            return "Giỏi";
        } else if (diemTrungBinh >= 7.0) {
            return "Khá";
        } else if (diemTrungBinh >= 5.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }

    /**
     * Ghi đè phương thức hienThiThongTin():
     * - Sử dụng super.hienThiThongTin() để hiển thị thông tin chung.
     * - Hiển thị bổ sung thông tin riêng của sinh viên.
     */
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println(" Mã sinh viên: " + maSinhVien);
        System.out.println(" Ngành học   : " + nganhHoc);
        System.out.printf(" Điểm TB     : %.2f%n", diemTrungBinh);
        System.out.println(" Xếp loại    : " + xepLoai());
    }
}
