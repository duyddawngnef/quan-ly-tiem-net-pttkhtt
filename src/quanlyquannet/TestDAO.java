package quanlyquannet;

import DAO.PhieuNhapHangDAO;
import DAL.DBConnect;
import DTO.PhieuNhapHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;




public class TestDAO {

    // Tạo mã PN theo thời gian để tránh trùng
    private static String genMaPhieuNhap() {
        return "PN" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyMMdd_HHmmss"));
    }

    public static void main(String[] args) {
        // Tạo mã phiếu nhập (CHỈ 1 LẦN)
        String maPN = genMaPhieuNhap();

        // Debug độ dài
        System.out.println("MaPN=" + maPN + " | length=" + maPN.length());

        // FK phải tồn tại trong DB
        String maNCC = "NCC001";
        String maNV  = "NV002";

        // Tạo DTO phiếu nhập
        PhieuNhapHangDTO pn = new PhieuNhapHangDTO();
        pn.setMaPhieuNhap(maPN);
        pn.setMaNCC(maNCC);
        pn.setMaNV(maNV);
        pn.setTongTien(BigDecimal.ZERO);
        pn.setTrangThai("CHODUYET");

        // Insert
        PhieuNhapHangDAO dao = new PhieuNhapHangDAO();
        boolean ok = dao.insert(pn);

        System.out.println("Insert PhieuNhap = " + ok + " | MaPN=" + maPN);

        // Kiểm tra DB có lên không
        String sqlCheck = "SELECT MaPhieuNhap, MaNCC, MaNV, NgayNhap, TongTien, TrangThai " +
                "FROM phieunhaphang WHERE MaPhieuNhap=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlCheck)) {

            ps.setString(1, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(" DB có phiếu nhập:");
                    System.out.println(
                            rs.getString("MaPhieuNhap") + " | " +
                                    rs.getString("MaNCC") + " | " +
                                    rs.getString("MaNV") + " | " +
                                    rs.getString("NgayNhap") + " | " +
                                    rs.getDouble("TongTien") + " | " +
                                    rs.getString("TrangThai")
                    );
                } else {
                    System.out.println(" Không tìm thấy phiếu nhập trong DB!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
