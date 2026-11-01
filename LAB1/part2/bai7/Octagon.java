package part2.bai7;

public class Octagon implements Polygon {
    private double side;

    public Octagon(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Cạnh bát giác phải lớn hơn 0!");
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 8 * side;
    }

    @Override
    public double area() {
        // Diện tích bát giác đều: S = 2 * (1 + sqrt(2)) * side^2
        return 2.0 * (1.0 + Math.sqrt(2.0)) * side * side;
    }

    @Override
    public String getName() {
        return "Hình Bát Giác Đều (Regular Octagon)";
    }

    @Override
    public String toString() {
        return String.format("%s [Cạnh=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), side, perimeter(), area());
    }
}
