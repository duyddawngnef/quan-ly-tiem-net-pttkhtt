package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PhieuNhapHangDTO {
    private String maPhieuNhap;
    private String maNCC;
    private String maNV;
    private Timestamp ngayNhap;      // datetime
    private BigDecimal tongTien;     // decimal(15,2)
    private String trangThai;        // CHODUYET/DANHAP/DAHUY

    public PhieuNhapHangDTO() {}

    public PhieuNhapHangDTO(String maPhieuNhap, String maNCC, String maNV,
                            Timestamp ngayNhap, BigDecimal tongTien, String trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getMaPhieuNhap() { return maPhieuNhap; }
    public void setMaPhieuNhap(String maPhieuNhap) { this.maPhieuNhap = maPhieuNhap; }

    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public Timestamp getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(Timestamp ngayNhap) { this.ngayNhap = ngayNhap; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
