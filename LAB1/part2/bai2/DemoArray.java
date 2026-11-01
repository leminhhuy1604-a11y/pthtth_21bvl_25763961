package part2.bai2;

public class DemoArray {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   BÀI 2: DEMO ĐỐI TƯỢNG MẢNG SỐ NGUYÊN (INT ARRAY)");
        System.out.println("==========================================================");

        // 1. Khởi tạo mảng có sức chứa tối đa n = 15 phần tử
        int n = 15;
        IntArray arr = new IntArray(n);
        System.out.println("1. Đã khởi tạo mảng với dung lượng tối đa n = " + n);

        // Khởi tạo các phần tử ban đầu
        int[] initialData = {170, 45, 75, 90, 802, 24, 2, 66};
        arr.setArray(initialData);

        // 2. Xuất thông tin mảng
        System.out.print("2. Thông tin ban đầu: ");
        arr.xuatThongTin();

        // 3. Thêm phần tử
        System.out.println("\n3. Thao tác thêm phần tử:");
        arr.themDau(999);
        System.out.print(" - Thêm 999 vào đầu: ");
        arr.xuatThongTin();

        arr.themCuoi(555);
        System.out.print(" - Thêm 555 vào cuối: ");
        arr.xuatThongTin();

        arr.themGiua(3, 888);
        System.out.print(" - Thêm 888 vào vị trí index 3: ");
        arr.xuatThongTin();

        // 4. Xóa phần tử theo giá trị X
        System.out.println("\n4. Thao tác xóa phần tử theo giá trị:");
        int x = 888;
        boolean deletedX = arr.xoaPhanTuX(x);
        System.out.println(" - Xóa phần tử có giá trị " + x + ": " + (deletedX ? "Thành công" : "Không tìm thấy"));
        arr.xuatThongTin();

        // 5. Xóa phần tử tại vị trí thứ j
        System.out.println("\n5. Thao tác xóa phần tử theo chỉ số (index):");
        int j = 0; // xóa phần tử đầu tiên
        arr.xoaTaiViTri(j);
        System.out.print(" - Xóa phần tử tại index " + j + ": ");
        arr.xuatThongTin();

        // 6. Tìm kiếm trong mảng chưa sắp xếp
        System.out.println("\n6. Tìm kiếm tuần tự trong mảng chưa sắp xếp:");
        int b1 = 75;
        int pos1 = arr.timKiemChuaSapXep(b1);
        System.out.println(" - Tìm " + b1 + ": " + (pos1 != -1 ? "Tìm thấy tại index " + pos1 : "Không tìm thấy"));

        int b2 = 1234;
        int pos2 = arr.timKiemChuaSapXep(b2);
        System.out.println(" - Tìm " + b2 + ": " + (pos2 != -1 ? "Tìm thấy tại index " + pos2 : "Không tìm thấy"));

        // 7. Sắp xếp mảng theo Radix Sort
        System.out.println("\n7. Sắp xếp mảng bằng Radix Sort:");
        arr.sapXepRadix(true); // Tăng dần
        System.out.print(" - Sắp xếp Radix tăng dần: ");
        arr.xuatThongTin();

        // 8. Tìm kiếm nhị phân trong mảng đã sắp xếp
        System.out.println("\n8. Tìm kiếm nhị phân trong mảng đã sắp xếp:");
        int b3 = 802;
        int pos3 = arr.timKiemDaSapXep(b3);
        System.out.println(" - Tìm " + b3 + ": " + (pos3 != -1 ? "Tìm thấy tại index " + pos3 : "Không tìm thấy"));

        int b4 = 100;
        int pos4 = arr.timKiemDaSapXep(b4);
        System.out.println(" - Tìm " + b4 + ": " + (pos4 != -1 ? "Tìm thấy tại index " + pos4 : "Không tìm thấy"));

        // Sắp xếp giảm dần
        arr.sapXepRadix(false); // Giảm dần
        System.out.print("\n - Sắp xếp Radix giảm dần: ");
        arr.xuatThongTin();
    }
}
