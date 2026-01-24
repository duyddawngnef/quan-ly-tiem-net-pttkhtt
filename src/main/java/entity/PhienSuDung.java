package com.quanlytiemnet.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PhienSuDung {
    // === Mapping trực tiếp với CSDL (filexuat.sql) ===
    private String maPhien;             // PK: varchar(20) - Tự động sinh PSxxx
    private String maKH;                // FK: varchar(20)
    private String maMay;               // FK: varchar(20)
    private String maNV;                // FK: varchar(20) (Trong SQL là MaNV)
    private String maGoiKH;             // FK: varchar(20) (Nullable)

    private LocalDateTime gioBatDau;    // datetime
    private LocalDateTime gioKetThuc;   // datetime (Nullable)

    private Double tongGio;             // decimal(10,2)
    private Double gioSuDungTuGoi;      // decimal(10,2)
    private Double gioSuDungTuTaiKhoan; // decimal(10,2)

    private BigDecimal giaMoiGio;       // decimal(10,2)
    private BigDecimal tienGioChoi;     // decimal(12,2)

    private String loaiThanhToan;       // Enum: TAIKHOAN, GOI, KETHOP
    private String trangThai;           // Enum: DANGCHOI, DAKETTHUC

    // === Các trường logic hỗ trợ hiển thị UI (Không lưu DB) ===
    private BigDecimal tienDichVu = BigDecimal.ZERO;
    private BigDecimal tongTien = BigDecimal.ZERO;

    // === Constructors ===
    public PhienSuDung() {
        // Constructor mặc định
    }

    // Constructor dùng khi Mở Phiên Mới
    public PhienSuDung(String maPhien, String maKH, String maMay, String maNV,
                       LocalDateTime gioBatDau, BigDecimal giaMoiGio) {
        this.maPhien = maPhien; // Mã này sẽ lấy từ hàm generateMaPhien() bên DAO
        this.maKH = maKH;
        this.maMay = maMay;
        this.maNV = maNV;
        this.gioBatDau = gioBatDau;
        this.giaMoiGio = giaMoiGio;

        // Giá trị mặc định ban đầu
        this.trangThai = "DANGCHOI";
        this.loaiThanhToan = "TAIKHOAN";
        this.tongGio = 0.0;
        this.gioSuDungTuGoi = 0.0;
        this.gioSuDungTuTaiKhoan = 0.0;
        this.tienGioChoi = BigDecimal.ZERO;
    }

    // === Các phương thức tính toán thời gian thực (Logic từ fileLamTrang13) ===
    // [cite: 440-446]
    public String getThoiGianDaChoi() {
        if (gioBatDau == null) return "0:00";
        LocalDateTime end = (gioKetThuc != null) ? gioKetThuc : LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(gioBatDau, end);
        return String.format("%d:%02d", minutes / 60, minutes % 60);
    }

    // [cite: 447-451]
    public BigDecimal getTienDuKien() {
        if (gioBatDau == null || giaMoiGio == null) return BigDecimal.ZERO;

        // Nếu đang dùng gói hoàn toàn (ví dụ combo đêm) thì tiền giờ phát sinh = 0
        if ("GOI".equals(loaiThanhToan)) return BigDecimal.ZERO;

        LocalDateTime end = (gioKetThuc != null) ? gioKetThuc : LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(gioBatDau, end);
        double gio = minutes / 60.0;

        return giaMoiGio.multiply(BigDecimal.valueOf(gio));
    }

    // === Getters and Setters ===
    public String getMaPhien() { return maPhien; }
    public void setMaPhien(String maPhien) { this.maPhien = maPhien; }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getMaMay() { return maMay; }
    public void setMaMay(String maMay) { this.maMay = maMay; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMaGoiKH() { return maGoiKH; }
    public void setMaGoiKH(String maGoiKH) { this.maGoiKH = maGoiKH; }

    public LocalDateTime getGioBatDau() { return gioBatDau; }
    public void setGioBatDau(LocalDateTime gioBatDau) { this.gioBatDau = gioBatDau; }

    public LocalDateTime getGioKetThuc() { return gioKetThuc; }
    public void setGioKetThuc(LocalDateTime gioKetThuc) { this.gioKetThuc = gioKetThuc; }

    public Double getTongGio() { return tongGio; }
    public void setTongGio(Double tongGio) { this.tongGio = tongGio; }

    public Double getGioSuDungTuGoi() { return gioSuDungTuGoi; }
    public void setGioSuDungTuGoi(Double gioSuDungTuGoi) { this.gioSuDungTuGoi = gioSuDungTuGoi; }

    public Double getGioSuDungTuTaiKhoan() { return gioSuDungTuTaiKhoan; }
    public void setGioSuDungTuTaiKhoan(Double gioSuDungTuTaiKhoan) { this.gioSuDungTuTaiKhoan = gioSuDungTuTaiKhoan; }

    public BigDecimal getGiaMoiGio() { return giaMoiGio; }
    public void setGiaMoiGio(BigDecimal giaMoiGio) { this.giaMoiGio = giaMoiGio; }

    public BigDecimal getTienGioChoi() { return tienGioChoi; }
    public void setTienGioChoi(BigDecimal tienGioChoi) { this.tienGioChoi = tienGioChoi; }

    public String getLoaiThanhToan() { return loaiThanhToan; }
    public void setLoaiThanhToan(String loaiThanhToan) { this.loaiThanhToan = loaiThanhToan; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public BigDecimal getTienDichVu() { return tienDichVu; }
    public void setTienDichVu(BigDecimal tienDichVu) { this.tienDichVu = tienDichVu; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }
}