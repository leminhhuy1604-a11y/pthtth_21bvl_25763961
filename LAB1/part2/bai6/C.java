package part2.bai6;

public class C extends B {
    public int x = 30; // Biến x của riêng lớp C (che giấu x của B và A)

    /**
     * CÁCH 1 (Ép kiểu tham chiếu / Upcasting):
     * Trong Java, biến thực thể (field) liên kết tĩnh theo kiểu tham chiếu tại compile-time (Field Hiding).
     * Do đó, bằng cách ép kiểu tham chiếu 'this' về 'A':
     * ((A) this).x = val;
     * Phương thức sẽ truy cập và gán trực tiếp cho biến x của lớp A
     * mà không làm thay đổi biến x của B hay C.
     */
    public void setXOfA_BangEpKieu(int val) {
        ((A) this).x = val;
    }

    public int getXOfA_BangEpKieu() {
        return ((A) this).x;
    }

    /**
     * CÁCH 2: Gọi phương thức kế thừa từ lớp A
     */
    public void setXOfA_BangMethod(int val) {
        setXA(val);
    }

    // Phương thức in ra giá trị x của cả 3 tầng kế thừa
    public void hienThiTatCaX() {
        System.out.println("  -> Giá trị x của lớp A: " + ((A) this).x);
        System.out.println("  -> Giá trị x của lớp B: " + ((B) this).x + " (hoặc super.x: " + super.x + ")");
        System.out.println("  -> Giá trị x của lớp C: " + this.x);
    }
}
