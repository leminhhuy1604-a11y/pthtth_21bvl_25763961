package part1.fileio;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DocFileNhiPhan {

    public static ArrayList<SinhVien> readSV(String src) throws IOException {
        ArrayList<SinhVien> listSV = new ArrayList<>();
        File file = new File(src);
        if (!file.exists()) {
            System.out.println("File không tồn tại: " + src);
            return listSV;
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            // Đọc số lượng sinh viên đã ghi
            int soLuongSV = dis.readInt();
            for (int i = 0; i < soLuongSV; i++) {
                String mssv = dis.readUTF();
                String ten = dis.readUTF();
                int tuoi = dis.readInt();

                // Đọc số lượng môn học của sinh viên
                int soLuongMH = dis.readInt();
                ArrayList<MonHoc> listMH = new ArrayList<>();
                for (int j = 0; j < soLuongMH; j++) {
                    String tenMH = dis.readUTF();
                    int tinChi = dis.readInt();
                    double diem = dis.readDouble();
                    listMH.add(new MonHoc(tenMH, tinChi, diem));
                }

                SinhVien sv = new SinhVien(mssv, ten, tuoi, listMH);
                listSV.add(sv);
            }
        }
        return listSV;
    }

    public static void main(String[] args) {
        String path = "sinhvien_data.bin";
        try {
            System.out.println("=== ĐỌC DỮ LIỆU TỪ FILE NHỊ PHÂN ===");
            ArrayList<SinhVien> list = readSV(path);
            System.out.println("Tổng số sinh viên đọc được: " + list.size());
            for (SinhVien sv : list) {
                System.out.println(sv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
