package part1.fileio;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tenMonHoc;
    private int tinChi;
    private double diem;

    public MonHoc() {
    }

    public MonHoc(String tenMonHoc, int tinChi, double diem) {
        this.tenMonHoc = tenMonHoc;
        this.tinChi = tinChi;
        this.diem = diem;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "Môn: " + tenMonHoc + " (" + tinChi + " TC) - Điểm: " + diem;
    }
}
