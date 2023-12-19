package stu.edu.vn.tahbookstore.model;

import java.io.Serializable;

public class SachBanChay implements Serializable {
    int ID;
    String TENSACH;
    String GIAGOC;
    String GIAGIAM;
    String MOTA;
    String HINHANH;
    int THELOAI;
    String TACGIA;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTENSACH() {
        return TENSACH;
    }

    public void setTENSACH(String TENSACH) {
        this.TENSACH = TENSACH;
    }

    public String getGIAGOC() {
        return GIAGOC;
    }

    public void setGIAGOC(String GIAGOC) {
        this.GIAGOC = GIAGOC;
    }

    public String getGIAGIAM() {
        return GIAGIAM;
    }

    public void setGIAGIAM(String GIAGIAM) {
        this.GIAGIAM = GIAGIAM;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }
    public int getTHELOAI() {
        return THELOAI;
    }

    public void setTHELOAI(int THELOAI) {
        this.THELOAI = THELOAI;
    }

    public String getTACGIA() {
        return TACGIA;
    }

    public void setTACGIA(String TACGIA) {
        this.TACGIA = TACGIA;
    }
}
