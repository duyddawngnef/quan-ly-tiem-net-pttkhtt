package dao;

import entity.PhienSuDung;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhienSuDungDAO {

    public List<PhienSuDung> getAll() {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung ORDER BY MaPhien DESC";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getAll: " + e.getMessage());
        }
        return list;
    }

    public PhienSuDung getById(String maPhien) {
        PhienSuDung phien = null;
        String sql = "SELECT * FROM phiensudung WHERE MaPhien = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maPhien);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                phien = mapResultSetToEntity(rs);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getById: " + e.getMessage());
        }
        return phien;
    }

    public PhienSuDung getPhienDangChay(String maMay) {
        PhienSuDung phien = null;
        // Kiểm tra đúng trạng thái 'DANGCHOI' trong enum của DB
        String sql = "SELECT * FROM phiensudung WHERE MaMay = ? AND TrangThai = 'DANGCHOI'";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maMay);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                phien = mapResultSetToEntity(rs);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getPhienDangChay: " + e.getMessage());
        }
        return phien;
    }

    // ==================== 2. CREATE (INSERT) ====================
    public boolean insert(PhienSuDung phien) {
        if (phien.getMamay() == null || phien.getMamay().isEmpty()) {
            throw new RuntimeException("Mã máy không được để trống!");
        }
        if (getPhienDangChay(phien.getMamay()) != null) {
            throw new RuntimeException("Máy " + phien.getMamay() + " đang có người sử dụng!");
        }

        String sql = "INSERT INTO phiensudung (MaPhien, MaKH, MaMay, MaNV, MaGoiKH, " +
                "GioBatDau, GiaMoiGio, TrangThai, LoaiThanhToan, TienGioChoi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String newId = generateMaPhien();
        phien.setMaphien(newId);
        phien.setTrangthai("DANGCHOI");
        if (phien.getLoaithanhtoan() == null) phien.setLoaithanhtoan("TAIKHOAN");

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, phien.getMaphien());

            // Xử lý MaKH null
            if (phien.getMakh() != null && !phien.getMakh().isEmpty()) {
                pstmt.setString(2, phien.getMakh());
            } else {
                pstmt.setNull(2, Types.VARCHAR);
            }

            pstmt.setString(3, phien.getMamay());
            pstmt.setString(4, phien.getManv()); // Trong DB là MaNV, không phải MaNVMoPhien

            if (phien.getMagoikh() != null && !phien.getMagoikh().isEmpty()) {
                pstmt.setString(5, phien.getMagoikh());
            } else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            pstmt.setTimestamp(6, Timestamp.valueOf(phien.getGiobatdau() != null ? phien.getGiobatdau() : LocalDateTime.now()));
            pstmt.setDouble(7, phien.getGiamoigio());
            pstmt.setString(8, "DANGCHOI");
            pstmt.setString(9, phien.getLoaithanhtoan());
            pstmt.setDouble(10, 0.0); // TienGioChoi ban đầu = 0

            int row = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi insert: " + e.getMessage());
        }
    }

    public boolean ketThucPhien(PhienSuDung phien) {
        PhienSuDung existing = getById(phien.getMaphien());
        if (existing == null) throw new RuntimeException("Phiên không tồn tại!");
        if ("DAKETTHUC".equals(existing.getTrangthai())) throw new RuntimeException("Phiên đã kết thúc rồi!");

        // CHỈNH SỬA: Bỏ update TienDichVu và TongTien vì DB không có cột đó
        String sql = "UPDATE phiensudung SET GioKetThuc = ?, TongGio = ?, " +
                "GioSuDungTuGoi = ?, GioSuDungTuTaiKhoan = ?, " +
                "TienGioChoi = ?, " + // Chỉ lưu tiền giờ chơi
                "LoaiThanhToan = ?, TrangThai = 'DAKETTHUC' " +
                "WHERE MaPhien = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setTimestamp(1, Timestamp.valueOf(phien.getGioketthuc() != null ? phien.getGioketthuc() : LocalDateTime.now()));
            pstmt.setDouble(2, phien.getTonggio());
            pstmt.setDouble(3, phien.getGiosudungtugoi());
            pstmt.setDouble(4, phien.getGiosudungtutaikhoan());
            pstmt.setDouble(5, phien.getTiengiochoi());
            pstmt.setString(6, phien.getLoaithanhtoan());
            pstmt.setString(7, phien.getMaphien());

            int row = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi ketThucPhien: " + e.getMessage());
        }
    }

    /**
     * Tính tổng tiền dịch vụ của phiên (Vì DB không lưu cột TienDichVu trong bang Phien,
     * nên ta phải tính tổng từ bảng SuDungDichVu)
     */
    public double getTongTienDichVu(String maPhien) {
        String sql = "SELECT SUM(ThanhTien) FROM sudungdichvu WHERE MaPhien = ?";
        double total = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maPhien);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
            rs.close(); pstmt.close(); conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public boolean huyPhien(String maPhien) {
        // Soft delete: Xóa phiên nhưng thực tế là đổi trạng thái để không mất dữ liệu audit
        // Nếu muốn hủy, ta có thể set thành DAKETTHUC với TongGio = 0
        String sql = "UPDATE phiensudung SET TrangThai = 'DAKETTHUC', GioKetThuc = NOW(), TongGio=0, TienGioChoi=0 WHERE MaPhien = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maPhien);
            int row = pstmt.executeUpdate();
            pstmt.close(); conn.close();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi huyPhien: " + e.getMessage());
        }
    }

    public String generateMaPhien() {
        String sql = "SELECT MaPhien FROM phiensudung ORDER BY MaPhien DESC LIMIT 1";
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String lastMa = rs.getString("MaPhien"); // VD: PS010
                if (lastMa.length() > 2) {
                    int num = Integer.parseInt(lastMa.substring(2));
                    rs.close(); stmt.close(); conn.close();
                    return String.format("PS%03d", num + 1);
                }
            }
            rs.close(); stmt.close(); conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "PS001";
    }

    private PhienSuDung mapResultSetToEntity(ResultSet rs) throws SQLException {
        PhienSuDung p = new PhienSuDung();
        p.setMaphien(rs.getString("MaPhien"));
        p.setMakh(rs.getString("MaKH"));
        p.setMamay(rs.getString("MaMay"));
        p.setManv(rs.getString("MaNV"));
        p.setMagoikh(rs.getString("MaGoiKH"));

        Timestamp start = rs.getTimestamp("GioBatDau");
        if (start != null) p.setGiobatdau(start.toLocalDateTime());

        Timestamp end = rs.getTimestamp("GioKetThuc");
        if (end != null) p.setGioketthuc(end.toLocalDateTime());

        p.setTonggio(rs.getDouble("TongGio"));
        p.setGiosudungtugoi(rs.getDouble("GioSuDungTuGoi"));
        p.setGiosudungtutaikhoan(rs.getDouble("GioSuDungTuTaiKhoan"));
        p.setGiamoigio(rs.getDouble("GiaMoiGio"));
        p.setTiengiochoi(rs.getDouble("TienGioChoi"));
        p.setLoaithanhtoan(rs.getString("LoaiThanhToan"));
        p.setTrangthai(rs.getString("TrangThai"));

        return p;
    }
}