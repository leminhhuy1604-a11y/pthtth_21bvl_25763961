package part2.bai7;

public class Rectangle extends Quadrilateral {
    protected double length;
    protected double width;

    public Rectangle(double length, double width) {
        super(length, width, length, width);
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Chiều dài và chiều rộng phải lớn hơn 0!");
        }
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public String getName() {
        return "Hình Chữ Nhật (Rectangle)";
    }

    @Override
    public String toString() {
        return String.format("%s [Dài=%.2f, Rộng=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), length, width, perimeter(), area());
    }
}
