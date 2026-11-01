# HƯỚNG DẪN CHẠY CÁC BÀI TẬP LAB 02 - OOP TRONG JAVA

Dự án gồm 2 bài tập về hai đặc tính cốt lõi của Lập trình Hướng đối tượng (OOP):
- **Bài 1**: Quản lý sản phẩm – **Tính đóng gói (Encapsulation)**
- **Bài 2**: Quản lý người trong trường đại học – **Tính kế thừa (Inheritance)**

---

## Cấu trúc thư mục

```
c:\pthtth_21bvl_25763961\LAB2\
├── bai1\
│   ├── SanPham.java     # Lớp SanPham với các thuộc tính private, getter/setter, nghiệp vụ nhập/bán hàng
│   └── Main.java        # Chương trình kiểm thử các kịch bản đóng gói và nghiệp vụ
└── bai2\
    ├── Nguoi.java        # Lớp cha cơ sở (họ tên, năm sinh, địa chỉ, tính tuổi)
    ├── SinhVien.java     # Lớp con kế thừa Nguoi (mã SV, ngành, điểm TB, xếp loại)
    ├── GiangVien.java    # Lớp con kế thừa Nguoi (mã GV, chuyên môn, lương CB, hệ số, tính lương)
    └── Main.java        # Chương trình quản lý danh sách sinh viên và giảng viên
```

---

## Hướng dẫn biên dịch và chạy bằng dòng lệnh (Terminal/PowerShell)

### Bài 1: Quản lý sản phẩm
```powershell
# Biên dịch các file trong package bai1
javac -encoding UTF-8 bai1\SanPham.java bai1\Main.java

# Chạy chương trình Bài 1
java "-Dfile.encoding=UTF-8" bai1.Main
```

### Bài 2: Quản lý người trong trường đại học
```powershell
# Biên dịch các file trong package bai2
javac -encoding UTF-8 bai2\Nguoi.java bai2\SinhVien.java bai2\GiangVien.java bai2\Main.java

# Chạy chương trình Bài 2
java "-Dfile.encoding=UTF-8" bai2.Main
```
