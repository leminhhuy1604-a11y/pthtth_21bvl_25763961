public class Product {
    private final String code;
    private final String name;
    private final double unitPrice;
    private final int quantity;

    public Product(String code, String name, double unitPrice, int quantity) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Mã sản phẩm không được rỗng");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tên sản phẩm không được rỗng");
        }
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Số lượng không được âm");
        }
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }

    public double inventoryValue() {
        return unitPrice * quantity;
    }

    public String toCsvLine() {
        return "%s,%s,%s,%d".formatted(code, name, unitPrice, quantity);
    }

    @Override
    public String toString() {
        return "%s - %s | Đơn giá: %,.0f | SL: %d | Giá trị tồn kho: %,.0f VND"
                .formatted(code, name, unitPrice, quantity, inventoryValue());
    }
}