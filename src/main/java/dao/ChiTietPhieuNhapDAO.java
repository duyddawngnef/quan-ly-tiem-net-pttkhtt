package DAO;

import DAL.DBConnect;
import DTO.ChiTietPhieuNhapDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhapDAO {

    private ChiTietPhieuNhapDTO map(ResultSet rs) throws SQLException {
        ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO();
        ct.setMaCTPN(rs.getString("MaCTPN"));
        ct.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
        ct.setMaDV(rs.getString("MaDV"));
        ct.setSoLuong(rs.getInt("SoLuong"));
        ct.setGiaNhap(rs.getBigDecimal("GiaNhap"));
        ct.setThanhTien(rs.getBigDecimal("ThanhTien"));
        return ct;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getByPhieu(String maPhieuNhap) {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap WHERE MaPhieuNhap=? ORDER BY MaCTPN";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhieuNhap);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ChiTietPhieuNhapDTO ct) {
        String sql = "INSERT INTO chitietphieunhap(MaCTPN, MaPhieuNhap, MaDV, SoLuong, GiaNhap, ThanhTien) VALUES(?,?,?,?,?,?)";

        BigDecimal thanhTien = ct.getThanhTien();
        if (thanhTien == null && ct.getGiaNhap() != null) {
            thanhTien = ct.getGiaNhap().multiply(BigDecimal.valueOf(ct.getSoLuong()));
        }

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ct.getMaCTPN());
            ps.setString(2, ct.getMaPhieuNhap());
            ps.setString(3, ct.getMaDV());
            ps.setInt(4, ct.getSoLuong());
            ps.setBigDecimal(5, ct.getGiaNhap());
            ps.setBigDecimal(6, thanhTien);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByPhieu(String maPhieuNhap) {
        String sql = "DELETE FROM chitietphieunhap WHERE MaPhieuNhap=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maPhieuNhap);
            return ps.executeUpdate() >= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
