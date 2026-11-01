package part2.bai7;

public class Pentagon implements Polygon {
    private double side;

    public Pentagon(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Cạnh ngũ giác phải lớn hơn 0!");
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 5 * side;
    }

    @Override
    public double area() {
        // Diện tích ngũ giác đều: S = (1/4) * sqrt(5 * (5 + 2 * sqrt(5))) * side^2
        return (0.25) * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * side * side;
    }

    @Override
    public String getName() {
        return "Hình Ngũ Giác Đều (Regular Pentagon)";
    }

    @Override
    public String toString() {
        return String.format("%s [Cạnh=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), side, perimeter(), area());
    }
}
