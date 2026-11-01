package part2.bai1;

public class DemoHinh {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BÀI 1: DEMO CÁC ĐỐI TƯỢNG HÌNH HỌC (OOP)");
        System.out.println("==================================================");

        // Khởi tạo không tham số
        System.out.println("--- 1. Khởi tạo đối tượng mặc định (không tham số) ---");
        HCN hcnMacDinh = new HCN();
        HVuong hvMacDinh = new HVuong();
        HTG htgMacDinh = new HTG();

        hcnMacDinh.xuatThongTin();
        hvMacDinh.xuatThongTin();
        htgMacDinh.xuatThongTin();

        // Khởi tạo đầy đủ tham số
        System.out.println("\n--- 2. Khởi tạo đối tượng đầy đủ tham số ---");
        HCN hcn = new HCN(8.5, 4.0);
        HVuong hv = new HVuong(6.0);
        HTG htg = new HTG(6.0, 8.0, 10.0);

        hcn.xuatThongTin();
        hv.xuatThongTin();
        htg.xuatThongTin();

        // Sử dụng các phương thức set và get
        System.out.println("\n--- 3. Thay đổi kích thước thông qua Setter & Getter ---");
        hcn.setChieuDai(12.0);
        hcn.setChieuRong(5.0);
        System.out.println("HCN sau khi set: Chiều dài = " + hcn.getChieuDai() + ", Chiều rộng = " + hcn.getChieuRong());
        hcn.xuatThongTin();

        hv.setCanh(7.5);
        System.out.println("HVuong sau khi set cạnh: " + hv.getCanh());
        hv.xuatThongTin();

        htg.setCacCanh(5.0, 5.0, 6.0);
        System.out.println("HTG sau khi set 3 cạnh: a=" + htg.getA() + ", b=" + htg.getB() + ", c=" + htg.getC());
        htg.xuatThongTin();
    }
}
