package com.quanlytiemnet.dao;

import com.quanlytiemnet.entity.PhienSuDung;
import com.quanlytiemnet.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhienSuDungDAO {

    // ==================== 1. SINH MÃ TỰ ĐỘNG ====================
    /**
     * Tự động sinh mã phiên mới theo format PS + 3 số (VD: PS001, PS015)
     * Tham khảo logic [cite: 2758-2769]
     */
    public String generateMaPhien() {
        String sql = "SELECT MaPhien FROM phiensudung ORDER BY MaPhien DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                String lastMa = rs.getString("MaPhien");
                if (lastMa.length() > 2) {
                    int num = Integer.parseInt(lastMa.substring(2));
                    return String.format("PS%03d", num + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "PS001"; // Mã mặc định nếu chưa có dữ liệu
    }

    // ==================== 2. MỞ PHIÊN (INSERT) ====================
    /**
     * Mở phiên sử dụng mới
     * Logic: Sinh mã -> Insert -> Trả về kết quả
     * Tham khảo [cite: 2884-2894]
     */
    public boolean insert(PhienSuDung p) {
        // 1. Sinh mã tự động nếu chưa có
        if (p.getMaPhien() == null || p.getMaPhien().isEmpty()) {
            p.setMaPhien(generateMaPhien());
        }

        String sql = "INSERT INTO phiensudung (MaPhien, MaKH, MaMay, MaNV, " +
                "GioBatDau, GiaMoiGio, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getMaPhien());

            // Xử lý MaKH null (Khách vãng lai)
            if (p.getMaKH() != null) {
                pstmt.setString(2, p.getMaKH());
            } else {
                pstmt.setNull(2, Types.VARCHAR);
            }

            pstmt.setString(3, p.getMaMay());
            pstmt.setString(4, p.getMaNVMoPhien()); // Map với cột MaNV trong DB
            pstmt.setTimestamp(5, Timestamp.valueOf(p.getGioBatDau()));
            pstmt.setBigDecimal(6, p.getGiaMoiGio());
            pstmt.setString(7, "DANGCHOI"); // Default theo logic nghiệp vụ

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi insert PhienSuDung: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // ==================== 3. LẤY PHIÊN ĐANG CHẠY ====================
    /**
     * Lấy phiên đang hoạt động của một máy cụ thể
     * Dùng để kiểm tra máy có đang online không
     */
    public PhienSuDung getPhienDangChay(String maMay) {
        String sql = "SELECT * FROM phiensudung WHERE MaMay = ? AND TrangThai = 'DANGCHOI'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maMay);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy danh sách tất cả các phiên đang hoạt động (Hiển thị sơ đồ máy)
     */
    public List<PhienSuDung> getAllPhienDangChay() {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung WHERE TrangThai = 'DANGCHOI'";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ==================== 4. KẾT THÚC PHIÊN (UPDATE) ====================
    /**
     * Cập nhật thông tin khi đóng phiên
     * Tham khảo logic [cite: 2901-2941]
     */
    public boolean ketThucPhien(PhienSuDung p) {
        String sql = "UPDATE phiensudung SET GioKetThuc=?, TongGio=?, " +
                "GioSuDungTuGoi=?, GioSuDungTuTaiKhoan=?, " +
                "TienGioChoi=?, TienDichVu=?, TongTien=?, " +
                "LoaiThanhToan=?, TrangThai='DAKETTHUC' WHERE MaPhien=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(p.getGioKetThuc()));
            pstmt.setDouble(2, p.getTongGio());
            pstmt.setDouble(3, p.getGioSuDungTuGoi());
            pstmt.setDouble(4, p.getGioSuDungTuTaiKhoan());
            pstmt.setBigDecimal(5, p.getTienGioChoi());
            pstmt.setBigDecimal(6, p.getTienDichVu());
            pstmt.setBigDecimal(7, p.getTongTien());
            pstmt.setString(8, p.getLoaiThanhToan());
            pstmt.setString(9, p.getMaPhien());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi ketThucPhien: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // ==================== 5. LỊCH SỬ & TIỆN ÍCH ====================
    /**
     * Lấy lịch sử chơi của khách hàng
     */
    public List<PhienSuDung> getByKhachHang(String maKH) {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung WHERE MaKH = ? ORDER BY GioBatDau DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maKH);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Helper: Cập nhật tiền dịch vụ vào phiên (khi gọi món)
     * Tham khảo [cite: 2347]
     */
    public boolean updateTienDichVu(String maPhien, java.math.BigDecimal tienDichVu) {
        String sql = "UPDATE phiensudung SET TienDichVu = ? WHERE MaPhien = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBigDecimal(1, tienDichVu);
            pstmt.setString(2, maPhien);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==================== 6. MAPPER ====================
    private PhienSuDung mapResultSetToEntity(ResultSet rs) throws SQLException {
        PhienSuDung p = new PhienSuDung();
        p.setMaPhien(rs.getString("MaPhien"));
        p.setMaKH(rs.getString("MaKH"));
        p.setMaMay(rs.getString("MaMay"));
        p.setMaNVMoPhien(rs.getString("MaNV")); // Cột DB là MaNV
        p.setMaGoiKH(rs.getString("MaGoiKH"));

        Timestamp start = rs.getTimestamp("GioBatDau");
        if (start != null) p.setGioBatDau(start.toLocalDateTime());

        Timestamp end = rs.getTimestamp("GioKetThuc");
        if (end != null) p.setGioKetThuc(end.toLocalDateTime());

        p.setTongGio(rs.getDouble("TongGio"));
        p.setGioSuDungTuGoi(rs.getDouble("GioSuDungTuGoi"));
        p.setGioSuDungTuTaiKhoan(rs.getDouble("GioSuDungTuTaiKhoan"));

        p.setGiaMoiGio(rs.getBigDecimal("GiaMoiGio"));
        p.setTienGioChoi(rs.getBigDecimal("TienGioChoi"));
        p.setTienDichVu(rs.getBigDecimal("TienDichVu"));
        p.setTongTien(rs.getBigDecimal("TongTien"));

        p.setLoaiThanhToan(rs.getString("LoaiThanhToan"));
        p.setTrangThai(rs.getString("TrangThai"));

        return p;
    }
}