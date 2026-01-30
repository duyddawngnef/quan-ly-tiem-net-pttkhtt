package DAO;

import DAL.DBConnect;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ThongkeDAO {

    // Kết quả doanh thu theo ngày (đơn giản)
    public static class DoanhThuNgay {
        public String ngay;        // yyyy-mm-dd
        public int soHoaDon;
        public BigDecimal doanhThu;

        public DoanhThuNgay(String ngay, int soHoaDon, BigDecimal doanhThu) {
            this.ngay = ngay;
            this.soHoaDon = soHoaDon;
            this.doanhThu = doanhThu;
        }
    }

    // Kết quả top dịch vụ
    public static class TopDichVu {
        public String maDV;
        public String tenDV;
        public BigDecimal tongSoLuong;
        public BigDecimal tongTien;

        public TopDichVu(String maDV, String tenDV, BigDecimal tongSoLuong, BigDecimal tongTien) {
            this.maDV = maDV;
            this.tenDV = tenDV;
            this.tongSoLuong = tongSoLuong;
            this.tongTien = tongTien;
        }
    }

    // doanh thu theo ngày (hóa đơn đã thanh toán)
    public ArrayList<DoanhThuNgay> doanhThuTheoNgay(Date from, Date to) {
        ArrayList<DoanhThuNgay> list = new ArrayList<>();
        String sql = """
                SELECT DATE(NgayLap) AS Ngay,
                       COUNT(*) AS SoHoaDon,
                       SUM(ThanhToan) AS DoanhThu
                FROM hoadon
                WHERE TrangThai = 'DATHANHTOAN'
                  AND NgayLap BETWEEN ? AND ?
                GROUP BY DATE(NgayLap)
                ORDER BY Ngay
                """;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, from);
            ps.setDate(2, to);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String ngay = rs.getString("Ngay");
                    int soHD = rs.getInt("SoHoaDon");
                    BigDecimal doanhThu = rs.getBigDecimal("DoanhThu");
                    list.add(new DoanhThuNgay(ngay, soHD, doanhThu == null ? BigDecimal.ZERO : doanhThu));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // top dịch vụ bán chạy theo thời gian (dựa vào sudungdichvu)
    public ArrayList<TopDichVu> topDichVu(Date from, Date to, int limit) {
        ArrayList<TopDichVu> list = new ArrayList<>();
        String sql = """
                SELECT dv.MaDV, dv.TenDV,
                       SUM(sd.SoLuong) AS TongSoLuong,
                       SUM(sd.ThanhTien) AS TongTien
                FROM sudungdichvu sd
                JOIN dichvu dv ON dv.MaDV = sd.MaDV
                WHERE sd.ThoiGian BETWEEN ? AND ?
                GROUP BY dv.MaDV, dv.TenDV
                ORDER BY TongSoLuong DESC
                LIMIT ?
                """;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, from);
            ps.setDate(2, to);
            ps.setInt(3, Math.max(1, limit));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new TopDichVu(
                            rs.getString("MaDV"),
                            rs.getString("TenDV"),
                            rs.getBigDecimal("TongSoLuong"),
                            rs.getBigDecimal("TongTien")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public BigDecimal tongDoanhThu(Date from, Date to) {
        String sql = """
                SELECT COALESCE(SUM(ThanhToan), 0) AS Tong
                FROM hoadon
                WHERE TrangThai = 'DATHANHTOAN'
                  AND NgayLap BETWEEN ? AND ?
                """;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, from);
            ps.setDate(2, to);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getBigDecimal("Tong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
}
