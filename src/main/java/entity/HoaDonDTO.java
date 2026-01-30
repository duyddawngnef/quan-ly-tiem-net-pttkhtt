package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HoaDonDTO {
    private String maHD;
    private String maPhien;
    private String maKH;
    private String maNV;
    private Timestamp ngayLap;

    private BigDecimal tienGioChoi;
    private BigDecimal tienDichVu;
    private BigDecimal tongTien;
    private BigDecimal giamGia;
    private BigDecimal thanhToan;

    private String phuongThucTT;
    private String trangThai;

    public HoaDonDTO() {}

    public HoaDonDTO(String maHD, String maPhien, String maKH, String maNV, Timestamp ngayLap,
                     BigDecimal tienGioChoi, BigDecimal tienDichVu, BigDecimal tongTien,
                     BigDecimal giamGia, BigDecimal thanhToan,
                     String phuongThucTT, String trangThai) {
        this.maHD = maHD;
        this.maPhien = maPhien;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayLap = ngayLap;
        this.tienGioChoi = tienGioChoi;
        this.tienDichVu = tienDichVu;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.thanhToan = thanhToan;
        this.phuongThucTT = phuongThucTT;
        this.trangThai = trangThai;
    }

    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getMaPhien() { return maPhien; }
    public void setMaPhien(String maPhien) { this.maPhien = maPhien; }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public Timestamp getNgayLap() { return ngayLap; }
    public void setNgayLap(Timestamp ngayLap) { this.ngayLap = ngayLap; }

    public BigDecimal getTienGioChoi() { return tienGioChoi; }
    public void setTienGioChoi(BigDecimal tienGioChoi) { this.tienGioChoi = tienGioChoi; }

    public BigDecimal getTienDichVu() { return tienDichVu; }
    public void setTienDichVu(BigDecimal tienDichVu) { this.tienDichVu = tienDichVu; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }

    public BigDecimal getGiamGia() { return giamGia; }
    public void setGiamGia(BigDecimal giamGia) { this.giamGia = giamGia; }

    public BigDecimal getThanhToan() { return thanhToan; }
    public void setThanhToan(BigDecimal thanhToan) { this.thanhToan = thanhToan; }

    public String getPhuongThucTT() { return phuongThucTT; }
    public void setPhuongThucTT(String phuongThucTT) { this.phuongThucTT = phuongThucTT; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
