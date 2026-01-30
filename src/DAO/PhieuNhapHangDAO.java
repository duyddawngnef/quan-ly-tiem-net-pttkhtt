package DAO;

import DAL.DBConnect;
import DTO.PhieuNhapHangDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class PhieuNhapHangDAO {

    private PhieuNhapHangDTO map(ResultSet rs) throws SQLException {
        PhieuNhapHangDTO pn = new PhieuNhapHangDTO();
        pn.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
        pn.setMaNCC(rs.getString("MaNCC"));
        pn.setMaNV(rs.getString("MaNV"));
        pn.setNgayNhap(rs.getTimestamp("NgayNhap"));
        pn.setTongTien(rs.getBigDecimal("TongTien"));
        pn.setTrangThai(rs.getString("TrangThai"));
        return pn;
    }

    public ArrayList<PhieuNhapHangDTO> getAll() {
        ArrayList<PhieuNhapHangDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieunhaphang ORDER BY NgayNhap DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(map(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public PhieuNhapHangDTO getById(String maPhieuNhap) {
        String sql = "SELECT * FROM phieunhaphang WHERE MaPhieuNhap=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhieuNhap);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<PhieuNhapHangDTO> getByNCC(String maNCC) {
        ArrayList<PhieuNhapHangDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieunhaphang WHERE MaNCC=? ORDER BY NgayNhap DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maNCC);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(PhieuNhapHangDTO pn) {
        String sql = "INSERT INTO phieunhaphang(MaPhieuNhap, MaNCC, MaNV, NgayNhap, TongTien, TrangThai) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pn.getMaPhieuNhap());
            ps.setString(2, pn.getMaNCC());
            ps.setString(3, pn.getMaNV());

            // nếu DTO dùng Timestamp thì set trực tiếp
            if (pn.getNgayNhap() != null) ps.setTimestamp(4, pn.getNgayNhap());
            else ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            ps.setBigDecimal(5, pn.getTongTien() == null ? BigDecimal.ZERO : pn.getTongTien());
            ps.setString(6, pn.getTrangThai() == null ? "CHODUYET" : pn.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // cập nhật tổng tiền theo chi tiết
    public boolean updateTongTienFromDetails(String maPhieuNhap) {
        String sql = """
                UPDATE phieunhaphang
                SET TongTien = (
                    SELECT COALESCE(SUM(ThanhTien), 0)
                    FROM chitietphieunhap
                    WHERE MaPhieuNhap = ?
                )
                WHERE MaPhieuNhap = ?
                """;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhieuNhap);
            ps.setString(2, maPhieuNhap);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTrangThai(String maPhieuNhap, String trangThai) {
        String sql = "UPDATE phieunhaphang SET TrangThai=? WHERE MaPhieuNhap=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            ps.setString(2, maPhieuNhap);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
