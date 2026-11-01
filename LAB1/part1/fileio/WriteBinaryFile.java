package part1.fileio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteBinaryFile {

    public static void saveSV(String src, ArrayList<SinhVien> listSV) throws IOException {
        File file = new File(src);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            // Ghi số lượng sinh viên
            dos.writeInt(listSV.size());
            for (SinhVien sv : listSV) {
                dos.writeUTF(sv.getMssv());
                dos.writeUTF(sv.getTen());
                dos.writeInt(sv.getTuoi());

                // Ghi số lượng môn học
                dos.writeInt(sv.getListMH().size());
                for (MonHoc mh : sv.getListMH()) {
                    dos.writeUTF(mh.getTenMonHoc());
                    dos.writeInt(mh.getTinChi());
                    dos.writeDouble(mh.getDiem());
                }
            }
            dos.flush();
        }
        System.out.println("Ghi danh sách sinh viên vào file nhị phân thành công: " + src);
    }

    public static void main(String[] args) throws IOException {
        MonHoc mh1 = new MonHoc("ltcb", 3, 6.7);
        MonHoc mh2 = new MonHoc("ltw", 3, 7.5);
        MonHoc mh3 = new MonHoc("tkhdt", 3, 8.0);

        ArrayList<MonHoc> listMH1 = new ArrayList<>();
        listMH1.add(mh1);
        listMH1.add(mh2);

        ArrayList<MonHoc> listMH2 = new ArrayList<>();
        listMH2.add(mh2);
        listMH2.add(mh3);

        ArrayList<SinhVien> listSV = new ArrayList<>();
        SinhVien sv1 = new SinhVien("11329078", "nguyen van A", 23, listMH1);
        SinhVien sv2 = new SinhVien("11329079", "nguyen Van B", 22, listMH2);
        listSV.add(sv1);
        listSV.add(sv2);

        saveSV("sinhvien_data.bin", listSV);
    }
}
