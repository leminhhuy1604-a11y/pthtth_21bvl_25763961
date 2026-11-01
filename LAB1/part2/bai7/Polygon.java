package part2.bai7;

public interface Polygon {
    /**
     * Tính diện tích của đa giác
     * @return diện tích (double)
     */
    double area();

    /**
     * Tính chu vi của đa giác
     * @return chu vi (double)
     */
    double perimeter();

    /**
     * Lấy tên loại đa giác
     */
    String getName();
}
