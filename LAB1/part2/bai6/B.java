package part2.bai6;

public class B extends A {
    public int x = 20; // Biến x của riêng lớp B (che giấu x của A)

    public void setXB(int val) {
        this.x = val;
    }

    public int getXB() {
        return this.x;
    }
}
