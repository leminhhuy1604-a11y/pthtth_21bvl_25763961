package bai2;


public class GiangVien extends Nguoi {
    // Thuộc tính bổ sung của GiangVien
    private String maGiangVien;
    private String chuyenMon;
    private double luongCoBan;
    private double heSoLuong;

    // Constructor mặc định
    public GiangVien() {
        super();
        this.maGiangVien = "";
        this.chuyenMon = "";
        this.luongCoBan = 0.0;
        this.heSoLuong = 1.0;
    }

    // Constructor đầy đủ tham số sử dụng super(...)
    public GiangVien(String hoTen, int namSinh, String diaChi,
                     String maGiangVien, String chuyenMon, double luongCoBan, double heSoLuong) {
        super(hoTen, namSinh, diaChi); // Gọi constructor của lớp cha Nguoi
        this.maGiangVien = maGiangVien;
        this.chuyenMon = chuyenMon;
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
    }

    // Getter và Setter cho các thuộc tính riêng
    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getChuyenMon() {
        return chuyenMon;
    }

    public void setChuyenMon(String chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        if (luongCoBan >= 0) {
            this.luongCoBan = luongCoBan;
        } else {
            System.out.println("[Cảnh báo] Lương cơ bản không được âm!");
        }
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        if (heSoLuong >= 0) {
            this.heSoLuong = heSoLuong;
        } else {
            System.out.println("[Cảnh báo] Hệ số lương không được âm!");
        }
    }

   
    public double tinhLuong() {
        return luongCoBan * heSoLuong;
    }

   
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println(" Mã giảng viên: " + maGiangVien);
        System.out.println(" Chuyên môn   : " + chuyenMon);
        System.out.printf(" Lương cơ bản : %,.0f VNĐ%n", luongCoBan);
        System.out.printf(" Hệ số lương  : %.2f%n", heSoLuong);
        System.out.printf(" Lương thực nhận: %,.0f VNĐ%n", tinhLuong());
    }
}
