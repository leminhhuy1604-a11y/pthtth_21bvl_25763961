package part2.bai3_4_5;

import java.util.Scanner;

public class Student extends Person {
    private double gpa;   // Điểm trung bình (0.0 đến 10.0)
    private String email; // Phải chứa '@' và không có khoảng trắng

    // Khởi tạo không tham số
    public Student() {
        super();
        this.gpa = 0.0;
        this.email = "";
    }

    // Khởi tạo đầy đủ tham số
    public Student(String name, String gender, String dob, String address, double gpa, String email) {
        super(name, gender, dob, address);
        setGpa(gpa);
        setEmail(email);
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 10.0) {
            throw new IllegalArgumentException("Điểm trung bình phải nằm trong khoảng từ 0.0 đến 10.0!");
        }
        this.gpa = gpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@") || email.contains(" ")) {
            throw new IllegalArgumentException("Email phải chứa ký tự '@' và không tồn tại khoảng trắng!");
        }
        this.email = email.trim();
    }

    // Xét xem Student có được học bổng không (Điểm trung bình trên 8.0 được học bổng)
    public boolean isHocBong() {
        return this.gpa > 8.0;
    }

    // Override phương thức inputInfo() nhập thông tin Student từ bàn phím
    @Override
    public void inputInfo(Scanner scanner) {
        // Gọi phương thức lớp cha để nhập thông tin cơ bản
        super.inputInfo(scanner);

        // Nhập và kiểm tra tính hợp lệ của GPA (0.0 - 10.0)
        while (true) {
            try {
                System.out.print("Nhập điểm trung bình (0.0 - 10.0): ");
                String inputGpa = scanner.nextLine().trim();
                double val = Double.parseDouble(inputGpa);
                if (val >= 0.0 && val <= 10.0) {
                    this.gpa = val;
                    break;
                } else {
                    System.out.println("(!) Điểm trung bình phải từ 0.0 đến 10.0. Vui lòng nhập lại!");
                }
            } catch (NumberFormatException e) {
                System.out.println("(!) Định dạng điểm không hợp lệ. Vui lòng nhập số thực!");
            }
        }

        // Nhập và kiểm tra email (chứa @ và không có khoảng trắng)
        while (true) {
            System.out.print("Nhập email: ");
            String inputEmail = scanner.nextLine().trim();
            if (inputEmail.contains("@") && !inputEmail.contains(" ")) {
                this.email = inputEmail;
                break;
            } else {
                System.out.println("(!) Email không hợp lệ! (Phải chứa ký tự '@' và không có khoảng trắng). Nhập lại!");
            }
        }
    }

    // Override phương thức printInfo() hiển thị tất cả thông tin Student
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.printf(" | ĐTB: %4.2f | Email: %-25s | Học bổng: %s%n",
                gpa, email, (isHocBong() ? "CÓ" : "Không"));
    }
}
