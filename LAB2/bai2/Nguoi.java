package bai2;

import java.time.Year;

/**
 * Lớp cha Nguoi - làm lớp cơ sở cho SinhVien và GiangVien.
 * Chứa các thông tin và hành vi chung của một con người trong trường đại học.
 */
public class Nguoi {
    // 1. Các thuộc tính private (tính đóng gói)
    private String hoTen;
    private int namSinh;
    private String diaChi;

    // Constructor mặc định
    public Nguoi() {
        this.hoTen = "";
        this.namSinh = Year.now().getValue();
        this.diaChi = "";
    }

    // Constructor đầy đủ tham số
    public Nguoi(String hoTen, int namSinh, String diaChi) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
    }

    // 2. Getter và Setter
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // 3. Phương thức tính tuổi
    public int tinhTuoi() {
        int namHienTai = Year.now().getValue();
        return namHienTai - namSinh;
    }

    // 4. Phương thức hiển thị thông tin chung
    public void hienThiThongTin() {
        System.out.println(" Họ và tên   : " + hoTen);
        System.out.println(" Năm sinh    : " + namSinh + " (" + tinhTuoi() + " tuổi)");
        System.out.println(" Địa chỉ     : " + diaChi);
    }
}
