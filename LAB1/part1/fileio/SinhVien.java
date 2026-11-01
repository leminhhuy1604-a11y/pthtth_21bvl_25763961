package part1.fileio;

import java.io.Serializable;
import java.util.ArrayList;

public class SinhVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mssv;
    private String ten;
    private int tuoi;
    private ArrayList<MonHoc> listMH;

    public SinhVien() {
        this.listMH = new ArrayList<>();
    }

    public SinhVien(String mssv, String ten, int tuoi, ArrayList<MonHoc> listMH) {
        this.mssv = mssv;
        this.ten = ten;
        this.tuoi = tuoi;
        this.listMH = listMH != null ? listMH : new ArrayList<>();
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public ArrayList<MonHoc> getListMH() {
        return listMH;
    }

    public void setListMH(ArrayList<MonHoc> listMH) {
        this.listMH = listMH;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sinh Viên [MSSV: %s, Tên: %s, Tuổi: %d]%n", mssv, ten, tuoi));
        sb.append("Danh sách môn học:\n");
        for (MonHoc mh : listMH) {
            sb.append("  - ").append(mh.toString()).append("\n");
        }
        return sb.toString();
    }
}
