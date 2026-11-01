package part2.bai7;

public class IsoscelesTriangle extends Triangle {
    protected double equalSide;
    protected double baseSide;

    public IsoscelesTriangle(double equalSide, double baseSide) {
        super(equalSide, equalSide, baseSide);
        this.equalSide = equalSide;
        this.baseSide = baseSide;
    }

    public double getEqualSide() {
        return equalSide;
    }

    public double getBaseSide() {
        return baseSide;
    }

    @Override
    public String getName() {
        return "Hình Tam Giác Cân (Isosceles Triangle)";
    }

    @Override
    public String toString() {
        return String.format("%s [2 Cạnh bên=%.2f, Cạnh đáy=%.2f => Chu vi=%.2f, Diện tích=%.2f]",
                getName(), equalSide, baseSide, perimeter(), area());
    }
}
