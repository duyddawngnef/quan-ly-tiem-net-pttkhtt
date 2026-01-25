package dao;

import entity.PhienSuDung;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhienSuDungDAO {

    // Lấy danh sách tất cả các phiên sử dụng, sắp xếp mới nhất lên đầu
    public List<PhienSuDung> getAll() {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung ORDER BY MaPhien DESC";
        try {
            // Kết nối database
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Duyệt qua từng dòng kết quả và thêm vào list
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }

            // Đóng kết nối
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getAll PhienSuDung: " + e.getMessage());
        }
        return list;
    }

    // Lấy thông tin chi tiết một phiên dựa vào Mã Phiên
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
            throw new RuntimeException("Lỗi getById PhienSuDung: " + e.getMessage());
        }
        return phien;
    }

    // Kiểm tra xem máy tính có đang được sử dụng hay không (Trạng thái DANGCHOI)
    public PhienSuDung getPhienDangChay(String maMay) {
        PhienSuDung phien = null;
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

    // Lấy danh sách toàn bộ các máy đang có người chơi (để vẽ sơ đồ máy)
    public List<PhienSuDung> getAllPhienDangChay() {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung WHERE TrangThai = 'DANGCHOI'";
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getAllPhienDangChay: " + e.getMessage());
        }
        return list;
    }

    // Xem lịch sử chơi của một khách hàng cụ thể
    public List<PhienSuDung> getByKhachHang(String maKH) {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung WHERE MaKH = ? ORDER BY GioBatDau DESC";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maKH);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getByKhachHang: " + e.getMessage());
        }
        return list;
    }

    // Thống kê danh sách phiên trong khoảng thời gian (từ ngày... đến ngày...)
    public List<PhienSuDung> getByDateRange(LocalDateTime from, LocalDateTime to) {
        List<PhienSuDung> list = new ArrayList<>();
        String sql = "SELECT * FROM phiensudung WHERE GioBatDau BETWEEN ? AND ? ORDER BY GioBatDau DESC";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, Timestamp.valueOf(from));
            pstmt.setTimestamp(2, Timestamp.valueOf(to));

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getByDateRange: " + e.getMessage());
        }
        return list;
    }

    // Thêm mới phiên sử dụng (Bắt đầu tính tiền)
    public boolean insert(PhienSuDung phien) {
        // Kiểm tra dữ liệu đầu vào
        if (phien.getMamay() == null || phien.getMamay().isEmpty()) {
            throw new RuntimeException("Mã máy không được để trống!");
        }

        // Kiểm tra máy có đang bận không trước khi mở
        if (getPhienDangChay(phien.getMamay()) != null) {
            throw new RuntimeException("Máy " + phien.getMamay() + " đang có người sử dụng!");
        }

        String sql = "INSERT INTO phiensudung (MaPhien, MaKH, MaMay, MaNV, MaGoiKH, " +
                "GioBatDau, GiaMoiGio, TrangThai, LoaiThanhToan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Tự động sinh mã phiên mới (VD: PS001)
        String newId = generateMaPhien();
        phien.setMaphien(newId);

        phien.setTrangthai("DANGCHOI");
        if (phien.getLoaithanhtoan() == null) phien.setLoaithanhtoan("TAIKHOAN");

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, phien.getMaphien());

            // Nếu không có khách hàng (khách vãng lai) thì set NULL
            if (phien.getMakh() != null && !phien.getMakh().isEmpty()) {
                pstmt.setString(2, phien.getMakh());
            } else {
                pstmt.setNull(2, Types.VARCHAR);
            }

            pstmt.setString(3, phien.getMamay());
            pstmt.setString(4, phien.getManv());

            // Nếu không dùng gói combo thì set NULL
            if (phien.getMagoikh() != null && !phien.getMagoikh().isEmpty()) {
                pstmt.setString(5, phien.getMagoikh());
            } else {
                pstmt.setNull(5, Types.VARCHAR);
            }

            // Lấy thời gian hiện tại làm giờ bắt đầu
            pstmt.setTimestamp(6, Timestamp.valueOf(phien.getGiobatdau() != null ? phien.getGiobatdau() : LocalDateTime.now()));
            pstmt.setDouble(7, phien.getGiamoigio());
            pstmt.setString(8, "DANGCHOI");
            pstmt.setString(9, phien.getLoaithanhtoan());

            // Thực thi lệnh insert
            int row = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return row > 0; // Trả về true nếu thêm thành công

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi insert PhienSuDung: " + e.getMessage());
        }
    }

    // Kết thúc phiên chơi, cập nhật giờ ra và tổng tiền
    public boolean ketThucPhien(PhienSuDung phien) {
        // Kiểm tra phiên có tồn tại và chưa kết thúc
        PhienSuDung existing = getById(phien.getMaphien());
        if (existing == null) throw new RuntimeException("Phiên không tồn tại!");
        if ("DAKETTHUC".equals(existing.getTrangthai())) throw new RuntimeException("Phiên đã kết thúc trước đó!");

        String sql = "UPDATE phiensudung SET GioKetThuc = ?, TongGio = ?, " +
                "GioSuDungTuGoi = ?, GioSuDungTuTaiKhoan = ?, " +
                "TienGioChoi = ?, TienDichVu = ?, TongTien = ?, " +
                "LoaiThanhToan = ?, TrangThai = 'DAKETTHUC' " +
                "WHERE MaPhien = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Cập nhật các thông tin tính toán cuối cùng
            pstmt.setTimestamp(1, Timestamp.valueOf(phien.getGioketthuc() != null ? phien.getGioketthuc() : LocalDateTime.now()));
            pstmt.setDouble(2, phien.getTonggio());
            pstmt.setDouble(3, phien.getGiosudungtugoi());
            pstmt.setDouble(4, phien.getGiosudungtutaikhoan());
            pstmt.setDouble(5, phien.getTiengiochoi());
            pstmt.setDouble(6, phien.getTiendichvu());
            pstmt.setDouble(7, phien.getTongtien());
            pstmt.setString(8, phien.getLoaithanhtoan());
            pstmt.setString(9, phien.getMaphien());

            int row = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi ketThucPhien: " + e.getMessage());
        }
    }

    // Cập nhật tổng tiền dịch vụ khi khách gọi món thêm
    public boolean updateTienDichVu(String maPhien, double tienDichVuMoi) {
        String sql = "UPDATE phiensudung SET TienDichVu = ? WHERE MaPhien = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, tienDichVuMoi);
            pstmt.setString(2, maPhien);

            int row = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi updateTienDichVu: " + e.getMessage());
        }
    }

    // Hủy phiên (xóa mềm) trường hợp mở nhầm
    public boolean huyPhien(String maPhien) {
        String sql = "UPDATE phiensudung SET TrangThai = 'DAHUY', GioKetThuc = NOW() WHERE MaPhien = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maPhien);

            int row = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi huyPhien: " + e.getMessage());
        }
    }

    // Hàm sinh mã tự động: Lấy mã cuối cùng + 1 (VD: PS009 -> PS010)
    public String generateMaPhien() {
        String sql = "SELECT MaPhien FROM phiensudung ORDER BY MaPhien DESC LIMIT 1";
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String lastMa = rs.getString("MaPhien");
                if (lastMa.length() > 2) {
                    int num = Integer.parseInt(lastMa.substring(2));

                    rs.close();
                    stmt.close();
                    conn.close();
                    return String.format("PS%03d", num + 1);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi generateMaPhien: " + e.getMessage());
        }
        return "PS001"; // Nếu chưa có dữ liệu thì trả về mã đầu tiên
    }

    // Chuyển dữ liệu từ ResultSet (Database) sang Object Entity (Java)
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
        p.setTiendichvu(rs.getDouble("TienDichVu"));
        p.setTongtien(rs.getDouble("TongTien"));

        p.setLoaithanhtoan(rs.getString("LoaiThanhToan"));
        p.setTrangthai(rs.getString("TrangThai"));

        return p;
    }

    // Hàm Main để chạy thử kiểm tra chức năng (Unit Test)
    public static void main(String[] args) {
        PhienSuDungDAO dao = new PhienSuDungDAO();
        System.out.println("=== TEST PHIENSUDUNG DAO ===");

        // Test sinh mã
        System.out.println("Mã phiên mới dự kiến: " + dao.generateMaPhien());

        // Test lấy danh sách
        List<PhienSuDung> list = dao.getAll();
        System.out.println("Tổng số phiên hiện có: " + list.size());
        for (PhienSuDung p : list) {
            System.out.println(p.getMaphien() + " - " + p.getMamay() + " - " + p.getTrangthai());
        }

        // Test lấy phiên đang chạy của một máy
        PhienSuDung dangChay = dao.getPhienDangChay("MAY001");
        if(dangChay != null) {
            System.out.println("Máy MAY001 đang hoạt động với phiên: " + dangChay.getMaphien());
        } else {
            System.out.println("Máy MAY001 đang trống.");
        }
    }
}