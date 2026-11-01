package part2.bai6;

public class DemoABC {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   BÀI 6: FIELD HIDING VÀ TRUY CẬP BIẾN CỦA LỚP TỔ TIÊN");
        System.out.println("==========================================================");

        C objC = new C();

        System.out.println("1. Trạng thái ban đầu của đối tượng lớp C:");
        objC.hienThiTatCaX();

        System.out.println("\n2. Gọi phương thức trong C: setXOfA_BangEpKieu(999)");
        System.out.println("   Thực hiện câu lệnh: ((A) this).x = 999;");
        objC.setXOfA_BangEpKieu(999);

        System.out.println("\n3. Kiểm tra lại giá trị sau khi gán:");
        objC.hienThiTatCaX();

        System.out.println("\n=> KẾT LUẬN:");
        if (((A) objC).x == 999 && objC.x == 30 && ((B) objC).x == 20) {
            System.out.println("✓ THÀNH CÔNG: Biến x của A đã được gán thành 999.");
            System.out.println("✓ Biến x của B vẫn giữ nguyên là " + ((B) objC).x);
            System.out.println("✓ Biến x của C vẫn giữ nguyên là " + objC.x);
        } else {
            System.out.println("✗ Thất bại.");
        }
    }
}
