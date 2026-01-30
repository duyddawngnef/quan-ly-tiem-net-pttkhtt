package DTO;

import java.math.BigDecimal;

public class ChiTietPhieuNhapDTO {
    private String maCTPN;
    private String maPhieuNhap;
    private String maDV;
    private int soLuong;
    private BigDecimal giaNhap;      // decimal(10,2)
    private BigDecimal thanhTien;    // decimal(12,2)

    public ChiTietPhieuNhapDTO() {}

    public ChiTietPhieuNhapDTO(String maCTPN, String maPhieuNhap, String maDV,
                               int soLuong, BigDecimal giaNhap, BigDecimal thanhTien) {
        this.maCTPN = maCTPN;
        this.maPhieuNhap = maPhieuNhap;
        this.maDV = maDV;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.thanhTien = thanhTien;
    }

    public String getMaCTPN() { return maCTPN; }
    public void setMaCTPN(String maCTPN) { this.maCTPN = maCTPN; }

    public String getMaPhieuNhap() { return maPhieuNhap; }
    public void setMaPhieuNhap(String maPhieuNhap) { this.maPhieuNhap = maPhieuNhap; }

    public String getMaDV() { return maDV; }
    public void setMaDV(String maDV) { this.maDV = maDV; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public BigDecimal getGiaNhap() { return giaNhap; }
    public void setGiaNhap(BigDecimal giaNhap) { this.giaNhap = giaNhap; }

    public BigDecimal getThanhTien() { return thanhTien; }
    public void setThanhTien(BigDecimal thanhTien) { this.thanhTien = thanhTien; }
}
