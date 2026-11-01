package part1.zoo;

public class Snake extends Animal {
    private double length; // Chiều dài (mét)

    public Snake() {
        super();
    }

    public Snake(String name, double weight, double length) {
        super(name, weight);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public void displayInfo() {
        System.out.printf("– Con rắn %s nặng %.0f cân và dài %.0f mét.%n", name, weight, length);
    }
}
