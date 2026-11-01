package part1.zoo;

import java.util.ArrayList;
import java.util.List;

public class ZooManagement {
    public static void main(String[] args) {
        System.out.println("=== QUẢN LÝ VẬT NUÔI TRONG VƯỜN THÚ ===");

        List<Animal> animals = new ArrayList<>();
        animals.add(new Lion("Leo", 300, 5));
        animals.add(new Snake("Boa", 50, 5));
        animals.add(new Monkey("George", 150, "chuối"));

        System.out.println("Danh sách động vật hiện có trong vườn thú:");
        for (Animal animal : animals) {
            animal.displayInfo();
        }
    }
}
