package part2.bai7;

public class Quadrilateral implements Polygon {
    protected double a;
    protected double b;
    protected double c;
    protected double d;

    public Quadrilateral(double a, double b, double c, double d) {
        if (a <= 0 || b <= 0 || c <= 0 || d <= 0) {
            throw new IllegalArgumentException("Các cạnh của tứ giác phải lớn hơn 0!");
        }
        // Điều kiện tồn tại tứ giác: mỗi cạnh phải nhỏ hơn tổng 3 cạnh còn lại
        if (a >= b + c + d || b >= a + c + d || c >= a + b + d || d >= a + b + c) {
            throw new IllegalArgumentException("Bốn cạnh không thể tạo thành một tứ giác hợp lệ!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public double getD() { return d; }

    @Override
    public double perimeter() {
        return a + b + c + d;
    }

    /**
     * Diện tích tứ giác lồi theo công thức Brahmagupta (áp dụng cho tứ giác nội tiếp đường tròn):
     * S = sqrt((s - a)*(s - b)*(s - c)*(s - d)) với s = (a+b+c+d)/2
     */
    @Override
    public double area() {
        double s = perimeter() / 2.0;
        return Math.sqrt((s - a) * (s - b) * (s - c) * (s - d));
    }

    @Override
    public String getName() {
        return "Hình Tứ Giác (Quadrilateral)";
    }

    @Override
    public String toString() {
        return String.format("%s [4 Cạnh a=%.2f, b=%.2f, c=%.2f, d=%.2f => Chu vi=%.2f, Diện tích (Brahmagupta)=%.2f]",
                getName(), a, b, c, d, perimeter(), area());
    }
}
