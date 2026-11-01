package part2.bai7;

public class EquilateralTriangle extends IsoscelesTriangle {
    private double side;

    public EquilateralTriangle(double side) {
        super(side, side);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 3 * side;
    }

    @Override
    public double area() {
        return (Math.sqrt(3) / 4.0) * side * side;
    }

    @Override
    public String getName() {
        return "Hình Tam Giác Đều (Equilateral Triangle)";
    }

    @Override
    public String toString() {
        return String.format("%s [Cạnh=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), side, perimeter(), area());
    }
}
