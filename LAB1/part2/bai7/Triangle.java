package part2.bai7;

public class Triangle implements Polygon {
    protected double a;
    protected double b;
    protected double c;

    public Triangle(double a, double b, double c) {
        if (!isValid(a, b, c)) {
            throw new IllegalArgumentException("Ba cạnh không thỏa mãn bất đẳng thức tam giác!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    protected boolean isValid(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 &&
               (a + b > c) && (a + c > b) && (b + c > a);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public double area() {
        double p = perimeter() / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String getName() {
        return "Hình Tam Giác Thường (Triangle)";
    }

    @Override
    public String toString() {
        return String.format("%s [Cạnh a=%.2f, b=%.2f, c=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), a, b, c, perimeter(), area());
    }
}
