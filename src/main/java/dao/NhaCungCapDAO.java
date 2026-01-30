package DAO;

import DAL.DBConnect;
import DTO.NhaCungCapDTO;

import java.sql.*;
import java.util.ArrayList;

public class NhaCungCapDAO {

    private NhaCungCapDTO map(ResultSet rs) throws SQLException {
        NhaCungCapDTO ncc = new NhaCungCapDTO();
        ncc.setMaNCC(rs.getString("MaNCC"));
        ncc.setTenNCC(rs.getString("TenNCC"));
        ncc.setSoDienThoai(rs.getString("SoDienThoai"));
        ncc.setEmail(rs.getString("Email"));
        ncc.setDiaChi(rs.getString("DiaChi"));
        ncc.setNguoiLienHe(rs.getString("NguoiLienHe"));
        ncc.setTrangThai(rs.getString("TrangThai"));
        return ncc;
    }

    public ArrayList<NhaCungCapDTO> getAll() {
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap ORDER BY TenNCC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(map(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhaCungCapDTO getById(String maNCC) {
        String sql = "SELECT * FROM nhacungcap WHERE MaNCC = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maNCC);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<NhaCungCapDTO> search(String keyword) {
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap WHERE TenNCC LIKE ? OR SoDienThoai LIKE ? OR Email LIKE ? OR DiaChi LIKE ? ORDER BY TenNCC";
        String like = "%" + (keyword == null ? "" : keyword.trim()) + "%";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(NhaCungCapDTO ncc) {
        String sql = "INSERT INTO nhacungcap(MaNCC, TenNCC, SoDienThoai, Email, DiaChi, NguoiLienHe, TrangThai) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ncc.getMaNCC());
            ps.setString(2, ncc.getTenNCC());
            ps.setString(3, ncc.getSoDienThoai());
            ps.setString(4, ncc.getEmail());
            ps.setString(5, ncc.getDiaChi());
            ps.setString(6, ncc.getNguoiLienHe());
            ps.setString(7, ncc.getTrangThai() == null ? "HOATDONG" : ncc.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(NhaCungCapDTO ncc) {
        String sql = "UPDATE nhacungcap SET TenNCC=?, SoDienThoai=?, Email=?, DiaChi=?, NguoiLienHe=?, TrangThai=? WHERE MaNCC=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getSoDienThoai());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setString(5, ncc.getNguoiLienHe());
            ps.setString(6, ncc.getTrangThai());
            ps.setString(7, ncc.getMaNCC());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // xóa mềm
    public boolean updateTrangThai(String maNCC, String trangThai) {
        String sql = "UPDATE nhacungcap SET TrangThai=? WHERE MaNCC=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            ps.setString(2, maNCC);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
