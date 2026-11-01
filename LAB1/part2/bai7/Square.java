package part2.bai7;

public class Square extends Rectangle {
    private double side;

    public Square(double side) {
        super(side, side);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 4 * side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public String getName() {
        return "Hình Vuông (Square)";
    }

    @Override
    public String toString() {
        return String.format("%s [Cạnh=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), side, perimeter(), area());
    }
}
