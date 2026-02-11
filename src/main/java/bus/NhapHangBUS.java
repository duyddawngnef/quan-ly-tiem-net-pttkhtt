package bus;

import dao.PhieuNhapHangDAO;
import entity.ChiTietPhieuNhap;
import entity.NhanVien;
import entity.PhieuNhapHang;

import java.util.List;

public class NhapHangBUS {

    private final PhieuNhapHangDAO pnDAO = new PhieuNhapHangDAO();

    private void requireQuanLy() throws Exception {
        NhanVien current = SessionManager.getCurrentUser();
        if (current == null) throw new Exception("Chưa đăng nhập");
        if (!"QUANLY".equalsIgnoreCase(current.getChucvu())) throw new Exception("Không có quyền thực hiện");
    }

    public String taoPhieuNhap(String maNCC, List<ChiTietPhieuNhap> chiTietList) throws Exception {
        requireQuanLy();

        // Theo nghiệp vụ: currentUser(MaNV)
        NhanVien current = SessionManager.getCurrentUser();
        String maNV = current.getManv();

        // Validation “đậm” đã nằm trong DAO createPhieuNhap của bạn,
        // BUS chỉ kiểm tra cơ bản trước:
        if (maNCC == null || maNCC.trim().isEmpty()) throw new Exception("Nhà cung cấp không hợp lệ");
        if (chiTietList == null || chiTietList.isEmpty()) throw new Exception("Chi tiết phiếu nhập không được rỗng");

        // DAO của bạn: tạo phiếu CHODUYET + insert chi tiết + update tổng tiền (transaction)
        return pnDAO.createPhieuNhap(maNCC, maNV, chiTietList);
    }

    public void duyetPhieu(String maPhieu) throws Exception {
        requireQuanLy();
        if (maPhieu == null || maPhieu.trim().isEmpty()) throw new Exception("Mã phiếu không hợp lệ");
        pnDAO.duyetPhieu(maPhieu);
    }

    public void huyPhieu(String maPhieu) throws Exception {
        requireQuanLy();
        if (maPhieu == null || maPhieu.trim().isEmpty()) throw new Exception("Mã phiếu không hợp lệ");
        pnDAO.huyPhieu(maPhieu);
    }

    public List<PhieuNhapHang> getAllPhieuNhap() throws Exception {
        requireQuanLy();
        return pnDAO.getAll();
    }
}
