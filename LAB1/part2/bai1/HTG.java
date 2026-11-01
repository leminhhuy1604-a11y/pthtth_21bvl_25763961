package part2.bai1;

public class HTG {
    private double a;
    private double b;
    private double c;

    // Khởi tạo không tham số (mặc định tam giác 3, 4, 5)
    public HTG() {
        this.a = 3.0;
        this.b = 4.0;
        this.c = 5.0;
    }

    // Khởi tạo đầy đủ tham số
    public HTG(double a, double b, double c) {
        if (!kiemTraHopLe(a, b, c)) {
            throw new IllegalArgumentException("Ba cạnh không tạo thành một tam giác hợp lệ!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private boolean kiemTraHopLe(double a, double b, double c) {
        return (a > 0 && b > 0 && c > 0) &&
               (a + b > c) && (a + c > b) && (b + c > a);
    }

    // Các phương thức get
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    // Các phương thức set
    public void setA(double a) {
        if (!kiemTraHopLe(a, this.b, this.c)) {
            throw new IllegalArgumentException("Giá trị cạnh a không hợp lệ!");
        }
        this.a = a;
    }

    public void setB(double b) {
        if (!kiemTraHopLe(this.a, b, this.c)) {
            throw new IllegalArgumentException("Giá trị cạnh b không hợp lệ!");
        }
        this.b = b;
    }

    public void setC(double c) {
        if (!kiemTraHopLe(this.a, this.b, c)) {
            throw new IllegalArgumentException("Giá trị cạnh c không hợp lệ!");
        }
        this.c = c;
    }

    public void setCacCanh(double a, double b, double c) {
        if (!kiemTraHopLe(a, b, c)) {
            throw new IllegalArgumentException("Ba cạnh không tạo thành một tam giác hợp lệ!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Phương thức tính Chu vi
    public double tinhChuVi() {
        return a + b + c;
    }

    // Phương thức tính Diện tích theo công thức Heron
    public double tinhDienTich() {
        double p = tinhChuVi() / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    // Phương thức xuất thông tin của đối tượng
    public void xuatThongTin() {
        System.out.printf("[Hình Tam Giác] Cạnh a: %.2f, b: %.2f, c: %.2f | Chu vi: %.2f | Diện tích: %.2f%n",
                a, b, c, tinhChuVi(), tinhDienTich());
    }

    @Override
    public String toString() {
        return String.format("HTG[a=%.2f, b=%.2f, c=%.2f, chuVi=%.2f, dienTich=%.2f]",
                a, b, c, tinhChuVi(), tinhDienTich());
    }
}
