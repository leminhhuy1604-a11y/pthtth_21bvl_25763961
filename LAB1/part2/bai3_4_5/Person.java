package part2.bai3_4_5;

import java.util.Scanner;

public class Person {
    protected String name;
    protected String gender;
    protected String dob;     // Ngày sinh (dd/MM/yyyy)
    protected String address; // Địa chỉ

    // Khởi tạo không tham số
    public Person() {
        this.name = "";
        this.gender = "";
        this.dob = "";
        this.address = "";
    }

    // Khởi tạo đầy đủ tham số
    public Person(String name, String gender, String dob, String address) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
    }

    // Các phương thức getter và setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Phương thức nhập thông tin Person từ bàn phím
    public void inputInfo(Scanner scanner) {
        System.out.print("Nhập họ và tên: ");
        this.name = scanner.nextLine().trim();

        System.out.print("Nhập giới tính (Nam/Nữ): ");
        this.gender = scanner.nextLine().trim();

        System.out.print("Nhập ngày sinh (VD: 15/08/2003): ");
        this.dob = scanner.nextLine().trim();

        System.out.print("Nhập địa chỉ: ");
        this.address = scanner.nextLine().trim();
    }

    // Phương thức hiển thị tất cả thông tin Person
    public void printInfo() {
        System.out.printf("Tên: %-20s | Giới tính: %-5s | Ngày sinh: %-10s | Địa chỉ: %-25s",
                name, gender, dob, address);
    }
}
