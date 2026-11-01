package part2.bai7;

public class Hexagon implements Polygon {
    private double side;

    public Hexagon(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Cạnh lục giác phải lớn hơn 0!");
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 6 * side;
    }

    @Override
    public double area() {
        // Diện tích lục giác đều: S = (3 * sqrt(3) / 2) * side^2
        return (3.0 * Math.sqrt(3) / 2.0) * side * side;
    }

    @Override
    public String getName() {
        return "Hình Lục Giác Đều (Regular Hexagon)";
    }

    @Override
    public String toString() {
        return String.format("%s [Cạnh=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), side, perimeter(), area());
    }
}
