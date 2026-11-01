# BÁO CÁO VÀ HƯỚNG DẪN THỰC HÀNH LAB 01: JAVA BASIC & REVIEW JAVA

Môn học: **Phát triển Hệ thống Thông tin**  
Đề tài: **Lab 01 - Java Basic & Review Java (OOP, Array, Inheritance, File IO, Interface)**

---

## 📁 CẤU TRÚC THƯ MỤC DỰ ÁN

```text
LAB1/
├── part1/                               # PHẦN 1: JAVA CƠ BẢN VÀ FILE/FOLDER IO
│   ├── basic/                           # Các bài tập cú pháp cơ bản
│   │   ├── HelloWorld.java              # 1. In "Hello, World!"
│   │   ├── Greeting.java                # 2. Nhập tên và in "Hi, I am..."
│   │   ├── SumAB.java                   # 3. Tính tổng A + B
│   │   ├── EvenOddCheck.java            # 4. Kiểm tra số chẵn hay lẻ
│   │   └── MonthToEnglish.java          # 5. Chuyển đổi tháng sang tiếng Anh (switch-case)
│   ├── zoo/                             # Quản lý vật nuôi vườn thú
│   │   ├── Animal.java                  # Lớp cha trừu tượng (name, weight)
│   │   ├── Lion.java                    # Sư tử (eat: số cân thịt/ngày)
│   │   ├── Snake.java                   # Rắn (length: chiều dài mét)
│   │   ├── Monkey.java                  # Khỉ (favoriteFood: thức ăn yêu thích)
│   │   └── ZooManagement.java           # Demo hiển thị thông tin các loài vật
│   └── fileio/                          # Thao tác với File / Folder
│       ├── DeleteFileDemo.java          # Xóa file
│       ├── DeleteFolderDemo.java        # Xóa thư mục (rỗng, chứa file, đệ quy thư mục con)
│       ├── SearchFileDemo.java          # Tìm kiếm file (finFile)
│       ├── CopyFileDemo.java            # Sao chép file dùng luồng byte
│       ├── MonHoc.java                  # Đối tượng Môn Học
│       ├── SinhVien.java                # Đối tượng Sinh Viên
│       ├── WriteBinaryFile.java         # Ghi danh sách sinh viên xuống file nhị phân
│       ├── ReadBinaryFile.java          # Đọc dữ liệu từ file nhị phân
│       └── ImageIODemo.java             # Đọc và ghi file ảnh với ImageIO
│
├── part2/                               # PHẦN 2: THỰC HÀNH LAB 01 – REVIEW JAVA
│   ├── bai1/                            # Bài 1: Hình học cơ bản (OOP)
│   │   ├── HCN.java                     # Hình chữ nhật
│   │   ├── HVuong.java                  # Hình vuông (kế thừa HCN)
│   │   ├── HTG.java                     # Hình tam giác
│   │   └── DemoHinh.java                # Demo khởi tạo và xuất thông tin
│   ├── bai2/                            # Bài 2: Mảng số nguyên tối đa n phần tử
│   │   ├── IntArray.java                # Lớp quản lý mảng, thêm, xóa, Radix sort, Linear & Binary search
│   │   └── DemoArray.java               # Demo toàn bộ thao tác mảng
│   ├── bai3_4_5/                        # Bài 3, 4, 5: Quản lý Person và Student
│   │   ├── Person.java                  # Bài 3: Lớp Person (name, gender, dob, address)
│   │   ├── Student.java                 # Bài 4: Lớp Student (gpa, email, xét học bổng)
│   │   └── StudentDemo.java             # Bài 5: Quản lý danh sách SV, Max/Min GPA, Học bổng
│   ├── bai6/                            # Bài 6: Field Hiding (Kế thừa A -> B -> C)
│   │   ├── A.java                       # Lớp A có biến instance x
│   │   ├── B.java                       # Lớp B extends A có biến instance x
│   │   ├── C.java                       # Lớp C extends B có biến instance x, phương thức set x của A
│   │   └── DemoABC.java                 # Demo kiểm chứng thay đổi x của A không ảnh hưởng B và C
│   └── bai7/                            # Bài 7: Interface Polygon và hệ thống đa giác
│       ├── Polygon.java                 # Interface Polygon: area(), perimeter(), getName()
│       ├── Triangle.java                # Tam giác thường
│       ├── IsoscelesTriangle.java       # Tam giác cân extends Triangle
│       ├── EquilateralTriangle.java     # Tam giác đều extends IsoscelesTriangle
│       ├── Quadrilateral.java           # Tứ giác tổng quát
│       ├── Rectangle.java               # Hình chữ nhật extends Quadrilateral
│       ├── Square.java                  # Hình vuông extends Rectangle
│       ├── Pentagon.java                # Ngũ giác đều
│       ├── Hexagon.java                 # Lục giác đều
│       ├── Octagon.java                 # Bát giác đều
│       ├── PolygonApp.java              # Giao diện người dùng dạng dòng lệnh (Console CLI Menu)
│       └── PolygonGUI.java              # Giao diện người dùng đồ họa trực quan (Swing GUI)
└── README.md
```

---

## 🚀 HƯỚNG DẪN BIÊN DỊCH VÀ CHẠY TOÀN BỘ CHƯƠNG TRÌNH

### 1. Biên dịch toàn bộ mã nguồn
Mở Terminal hoặc PowerShell tại thư mục `c:\pthtth_21bvl_25763961\LAB1` và chạy:
```powershell
javac -encoding UTF-8 part1/basic/*.java part1/zoo/*.java part1/fileio/*.java part2/bai1/*.java part2/bai2/*.java part2/bai3_4_5/*.java part2/bai6/*.java part2/bai7/*.java
```

---

### 2. Chạy từng phần và bài tập

#### PHẦN 1: CÁC VÍ DỤ CƠ BẢN & FILE IO
- **1. Hello World:**
  ```powershell
  java -cp . part1.basic.HelloWorld
  ```
- **2. Lời chào tên:**
  ```powershell
  java -cp . part1.basic.Greeting
  ```
- **3. Tính tổng A + B:**
  ```powershell
  java -cp . part1.basic.SumAB
  ```
- **4. Kiểm tra chẵn lẻ:**
  ```powershell
  java -cp . part1.basic.EvenOddCheck
  ```
- **5. Đổi tháng sang tiếng Anh:**
  ```powershell
  java -cp . part1.basic.MonthToEnglish
  ```
- **6. Quản lý vật nuôi Vườn Thú:**
  ```powershell
  java -cp . part1.zoo.ZooManagement
  ```
- **7. Thao tác File & Folder:**
  - Xóa folder: `java -cp . part1.fileio.DeleteFolderDemo`
  - Tìm kiếm file: `java -cp . part1.fileio.SearchFileDemo`
  - Copy file: `java -cp . part1.fileio.CopyFileDemo`
  - Ghi file nhị phân: `java -cp . part1.fileio.WriteBinaryFile`
  - Đọc file nhị phân: `java -cp . part1.fileio.ReadBinaryFile`
  - Đọc/Ghi file ảnh: `java -cp . part1.fileio.ImageIODemo`

---

#### PHẦN 2: THỰC HÀNH REVIEW JAVA (BÀI 1 ĐẾN BÀI 7)

- **Bài 1: Demo các đối tượng hình học (HCN, HVuong, HTG):**
  ```powershell
  java "-Dfile.encoding=UTF-8" -cp . part2.bai1.DemoHinh
  ```

- **Bài 2: Demo mảng số nguyên tối đa n phần tử (Radix sort, chèn/xóa, tìm kiếm):**
  ```powershell
  java "-Dfile.encoding=UTF-8" -cp . part2.bai2.DemoArray
  ```

- **Bài 3, 4, 5: Demo Quản lý Sinh viên (Person -> Student -> StudentDemo):**
  ```powershell
  java "-Dfile.encoding=UTF-8" -cp . part2.bai3_4_5.StudentDemo
  ```

- **Bài 6: Demo Field Hiding (Lớp A, B, C và phương thức trong C gán x của A):**
  ```powershell
  java "-Dfile.encoding=UTF-8" -cp . part2.bai6.DemoABC
  ```

- **Bài 7: Giao diện người dùng Đa giác (Polygon App):**
  - **Console Menu CLI:**
    ```powershell
    java "-Dfile.encoding=UTF-8" -cp . part2.bai7.PolygonApp
    ```
  - **Giao diện đồ họa Swing (GUI):**
    ```powershell
    java "-Dfile.encoding=UTF-8" -cp . part2.bai7.PolygonGUI
    ```

---

## 📖 GIẢI THÍCH CHI TIẾT CÁC CÂU HỎI LÝ THUYẾT VÀ BÀI TẬP ĐẶC BIỆT

### 1. Câu hỏi đọc/ghi file nhị phân (Part 1):
> *"Để đọc file nhị phân mình cần biết những gì?"*

- **Cấu trúc dữ liệu đã ghi (Schema / Format):** File nhị phân lưu trữ dưới dạng các byte liên tiếp mà không có dấu phân cách rõ ràng như file văn bản (JSON, XML hay CSV). Do đó, người đọc bắt buộc phải nắm rõ:
  1. **Thứ tự các trường:** Ví dụ ghi `MSSV` trước, rồi `Tên`, rồi `Tuổi`, rồi `Số lượng môn học`, sau đó là từng môn học.
  2. **Kiểu dữ liệu và kích thước của từng trường:** Ví dụ `int` (4 byte), `double` (8 byte), `UTF-8 String` (2 byte độ dài + chuỗi byte UTF-8).
  3. **Số lượng bản ghi hoặc dấu hiệu kết thúc (EOF):** Cần ghi số lượng bản ghi ở đầu file (như `dos.writeInt(listSV.size())`) hoặc đọc cho đến khi bắt gặp ngoại lệ `EOFException`.
- **Cách giải quyết:** Sử dụng luồng `DataInputStream` bọc `FileInputStream` và gọi các hàm đọc tương ứng theo đúng thứ tự (`readInt()`, `readUTF()`, `readDouble()`).

---

### 2. Phân tích Bài 6: Field Hiding & Truy cập biến x của lớp A từ C
- **Hiện tượng Field Hiding (Che giấu biến):** Khi lớp con định nghĩa một biến instance có cùng tên với biến của lớp cha, biến của lớp con sẽ che giấu (hide) biến của lớp cha. Khác với phương thức (method overriding liên kết động runtime qua đa hình), biến thành viên được liên kết tĩnh (static binding) tại thời điểm biên dịch dựa trên **kiểu tham chiếu (Reference Type)**.
- **Vấn đề đặt ra:** Trong lớp `C`, `this.x` tham chiếu đến `x` của C; `super.x` tham chiếu đến `x` của B. Trong Java **không tồn tại** cú pháp `super.super.x`.
- **Giải pháp:**
  - **Cách 1 (Ép kiểu tham chiếu `this` về `A`):**
    ```java
    ((A) this).x = value;
    ```
    Do kiểu tham chiếu được ép về `A`, trình biên dịch sẽ phát sinh chỉ lệnh bytecode `putfield A.x`, trực tiếp ghi đè vào ô nhớ của thuộc tính `x` thuộc lớp `A` của đối tượng, mà hoàn toàn **không đụng chạm** đến biến `x` của `B` hay `C`.
  - **Cách 2 (Sử dụng phương thức setter trong A):** Lớp `A` cung cấp `public void setXA(int val) { this.x = val; }`. Lớp `C` gọi phương thức kế thừa này mà không bị override ở B.

---

### 3. Cấu trúc kế thừa và công thức toán học Bài 7 (Polygon):
1. **Quan hệ kế thừa:**
   - `Polygon` *(Interface)*: định nghĩa hợp đồng `area()`, `perimeter()`.
   - `Triangle implements Polygon`:
     - `IsoscelesTriangle extends Triangle` (Tam giác cân).
     - `EquilateralTriangle extends IsoscelesTriangle` (Tam giác đều).
   - `Quadrilateral implements Polygon` (Tứ giác):
     - `Rectangle extends Quadrilateral` (Hình chữ nhật).
     - `Square extends Rectangle` (Hình vuông).
   - `Pentagon implements Polygon` (Ngũ giác đều).
   - `Hexagon implements Polygon` (Lục giác đều).
   - `Octagon implements Polygon` (Bát giác đều).
2. **Công thức tính toán:**
   - **Tam giác thường:** Chu vi $P = a + b + c$, Diện tích Heron $S = \sqrt{p(p-a)(p-b)(p-c)}$ với $p = P/2$.
   - **Tam giác đều cạnh $s$:** $P = 3s$, $S = \frac{\sqrt{3}}{4} s^2$.
   - **Hình chữ nhật ($l, w$):** $P = 2(l + w)$, $S = l \times w$.
   - **Hình vuông cạnh $s$:** $P = 4s$, $S = s^2$.
   - **Ngũ giác đều cạnh $s$:** $P = 5s$, $S = \frac{1}{4}\sqrt{5(5 + 2\sqrt{5})} s^2 \approx 1.7205 s^2$.
   - **Lục giác đều cạnh $s$:** $P = 6s$, $S = \frac{3\sqrt{3}}{2} s^2 \approx 2.5981 s^2$.
   - **Bát giác đều cạnh $s$:** $P = 8s$, $S = 2(1 + \sqrt{2}) s^2 \approx 4.8284 s^2$.
