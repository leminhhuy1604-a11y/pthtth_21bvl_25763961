package part2.bai2;

import java.util.Arrays;
import java.util.Scanner;

public class IntArray {
    private int[] a;
    private int size;
    private int capacity; // Số phần tử tối đa n

    // Khởi tạo mảng với dung lượng tối đa n
    public IntArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Dung lượng tối đa phải > 0");
        }
        this.capacity = capacity;
        this.a = new int[capacity];
        this.size = 0;
    }

    public IntArray() {
        this(100);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public int[] getArrayCopy() {
        return Arrays.copyOf(a, size);
    }

    // Nhập giá trị cho mảng
    public void nhapMang(Scanner scanner) {
        System.out.print("Nhập số lượng phần tử ban đầu (<= " + capacity + "): ");
        int n = scanner.nextInt();
        while (n < 0 || n > capacity) {
            System.out.print("Số lượng không hợp lệ! Vui lòng nhập lại (0 <= n <= " + capacity + "): ");
            n = scanner.nextInt();
        }
        this.size = n;
        for (int i = 0; i < this.size; i++) {
            System.out.print("Nhập phần tử thứ " + i + ": ");
            a[i] = scanner.nextInt();
        }
    }

    // Nạp sẵn mảng từ tham số (tiện cho demo và test)
    public void setArray(int[] initial) {
        if (initial.length > capacity) {
            throw new IllegalArgumentException("Dữ liệu nạp vượt quá dung lượng tối đa n=" + capacity);
        }
        System.arraycopy(initial, 0, this.a, 0, initial.length);
        this.size = initial.length;
    }

    // Xuất thông tin của mảng
    public void xuatThongTin() {
        System.out.print("Mảng [size=" + size + "/" + capacity + "]: ");
        if (size == 0) {
            System.out.println("[] (Mảng rỗng)");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + (i < size - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // Thêm một phần tử Y vào đầu mảng (vị trí 0)
    public boolean themDau(int y) {
        return themGiua(0, y);
    }

    // Thêm một phần tử Y vào cuối mảng (vị trí size)
    public boolean themCuoi(int y) {
        return themGiua(size, y);
    }

    // Thêm một phần tử Y vào giữa (vị trí thứ i) của mảng (0 <= i <= size)
    public boolean themGiua(int i, int y) {
        if (size >= capacity) {
            System.out.println("Lỗi: Mảng đã đầy (đạt dung lượng tối đa n=" + capacity + "), không thể thêm!");
            return false;
        }
        if (i < 0 || i > size) {
            System.out.println("Lỗi: Vị trí chèn " + i + " không hợp lệ (hợp lệ: 0 <= i <= " + size + ")");
            return false;
        }
        // Dịch chuyển các phần tử từ vị trí i sang phải 1 đơn vị
        for (int k = size; k > i; k--) {
            a[k] = a[k - 1];
        }
        a[i] = y;
        size++;
        return true;
    }

    // Xóa phần tử X trong mảng (xóa tất cả các phần tử có giá trị X)
    public boolean xoaPhanTuX(int x) {
        boolean found = false;
        int i = 0;
        while (i < size) {
            if (a[i] == x) {
                xoaTaiViTri(i);
                found = true;
            } else {
                i++;
            }
        }
        return found;
    }

    // Xóa phần tử thứ j trong mảng (chỉ số j: 0 <= j < size)
    public boolean xoaTaiViTri(int j) {
        if (j < 0 || j >= size) {
            System.out.println("Lỗi: Vị trí xóa " + j + " không hợp lệ (hợp lệ: 0 <= j < " + size + ")");
            return false;
        }
        for (int k = j; k < size - 1; k++) {
            a[k] = a[k + 1];
        }
        size--;
        return true;
    }

    // Sắp xếp mảng tăng dần hoặc giảm dần theo phương pháp Radix Sort
    // Hỗ trợ cả số nguyên âm bằng kỹ thuật offset shift
    public void sapXepRadix(boolean tangDan) {
        if (size <= 1) return;

        // 1. Tìm giá trị nhỏ nhất để dịch chuyển tất cả về số không âm
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < size; i++) {
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }

        // Tạo mảng tạm với các giá trị shifted >= 0
        long[] shifted = new long[size];
        long maxShifted = 0;
        for (int i = 0; i < size; i++) {
            shifted[i] = (long) a[i] - min;
            if (shifted[i] > maxShifted) {
                maxShifted = shifted[i];
            }
        }

        // 2. Chạy Radix Sort (LSD) theo từng chữ số hàng 1, 10, 100,...
        for (long exp = 1; maxShifted / exp > 0; exp *= 10) {
            countingSortByDigit(shifted, exp);
        }

        // 3. Khôi phục lại mảng gốc từ shifted
        for (int i = 0; i < size; i++) {
            a[i] = (int) (shifted[i] + min);
        }

        // 4. Nếu yêu cầu giảm dần, đảo ngược mảng
        if (!tangDan) {
            for (int i = 0; i < size / 2; i++) {
                int temp = a[i];
                a[i] = a[size - 1 - i];
                a[size - 1 - i] = temp;
            }
        }
    }

    private void countingSortByDigit(long[] arr, long exp) {
        long[] output = new long[size];
        int[] count = new int[10];

        for (int i = 0; i < size; i++) {
            int digit = (int) ((arr[i] / exp) % 10);
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = size - 1; i >= 0; i--) {
            int digit = (int) ((arr[i] / exp) % 10);
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, size);
    }

    // Tìm kiếm một phần tử B trong mảng chưa sắp xếp (Linear Search)
    // Trả về chỉ số vị trí tìm thấy đầu tiên, hoặc -1 nếu không tìm thấy
    public int timKiemChuaSapXep(int b) {
        for (int i = 0; i < size; i++) {
            if (a[i] == b) {
                return i;
            }
        }
        return -1;
    }

    // Tìm kiếm một phần tử B trong mảng đã sắp xếp (Binary Search)
    // Trả về chỉ số vị trí tìm thấy, hoặc -1 nếu không tìm thấy
    public int timKiemDaSapXep(int b) {
        if (size == 0) return -1;
        // Kiểm tra xem mảng đang tăng dần hay giảm dần
        boolean isAscending = true;
        if (size > 1 && a[0] > a[size - 1]) {
            isAscending = false;
        }

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == b) {
                return mid;
            }
            if (isAscending) {
                if (a[mid] < b) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // Mảng giảm dần
                if (a[mid] < b) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
