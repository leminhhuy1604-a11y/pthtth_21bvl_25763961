package part1.zoo;

public class Lion extends Animal {
    private double eat; // Số cân thịt ăn mỗi ngày

    public Lion() {
        super();
    }

    public Lion(String name, double weight, double eat) {
        super(name, weight);
        this.eat = eat;
    }

    public double getEat() {
        return eat;
    }

    public void setEat(double eat) {
        this.eat = eat;
    }

    @Override
    public void displayInfo() {
        System.out.printf("– Sư tử %s nặng %.0f cân và ăn %.0f cân thịt mỗi ngày.%n", name, weight, eat);
    }
}
