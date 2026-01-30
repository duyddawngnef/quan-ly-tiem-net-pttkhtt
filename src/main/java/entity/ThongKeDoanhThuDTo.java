package DTO;

import java.math.BigDecimal;

public class ThongKeDoanhThuDTO {
    private String nhomThoiGian;     // ví dụ: "2026-01-14" hoặc "2026-01"
    private int soHoaDon;
    private BigDecimal tongDoanhThu;

    public ThongKeDoanhThuDTO() {}

    public ThongKeDoanhThuDTO(String nhomThoiGian, int soHoaDon, BigDecimal tongDoanhThu) {
        this.nhomThoiGian = nhomThoiGian;
        this.soHoaDon = soHoaDon;
        this.tongDoanhThu = tongDoanhThu;
    }

    public String getNhomThoiGian() { return nhomThoiGian; }
    public void setNhomThoiGian(String nhomThoiGian) { this.nhomThoiGian = nhomThoiGian; }

    public int getSoHoaDon() { return soHoaDon; }
    public void setSoHoaDon(int soHoaDon) { this.soHoaDon = soHoaDon; }

    public BigDecimal getTongDoanhThu() { return tongDoanhThu; }
    public void setTongDoanhThu(BigDecimal tongDoanhThu) { this.tongDoanhThu = tongDoanhThu; }
}
