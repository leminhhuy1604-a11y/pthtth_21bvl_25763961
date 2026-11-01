package part1.zoo;

public class Monkey extends Animal {
    private String favoriteFood; // Thức ăn yêu thích

    public Monkey() {
        super();
    }

    public Monkey(String name, double weight, String favoriteFood) {
        super(name, weight);
        this.favoriteFood = favoriteFood;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void displayInfo() {
        System.out.printf("– Con khỉ %s nặng %.0f cân và thích ăn %s%n", name, weight, favoriteFood);
    }
}
