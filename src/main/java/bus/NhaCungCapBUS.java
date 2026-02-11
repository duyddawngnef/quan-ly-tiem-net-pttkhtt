package bus;

import dao.NhaCungCapDAO;
import entity.NhaCungCap;
import entity.NhanVien;

import java.util.List;

public class NhaCungCapBUS {

    private final NhaCungCapDAO dao = new NhaCungCapDAO();

    private void requireQuanLy() throws Exception {
        NhanVien current = SessionManager.getCurrentUser();
        if (current == null) throw new Exception("Chưa đăng nhập");
        if (!"QUANLY".equalsIgnoreCase(current.getChucvu())) throw new Exception("Không có quyền thực hiện");
    }

    public List<NhaCungCap> getAllNhaCungCap() throws Exception {
        requireQuanLy();
        return dao.getAll(true);
    }

    public List<NhaCungCap> getNhaCungCapHoatDong() throws Exception {
        requireQuanLy();
        return dao.getAll(false);
    }

    public String themNhaCungCap(NhaCungCap ncc) throws Exception {
        requireQuanLy();
        return dao.insert(ncc);
    }

    public boolean suaNhaCungCap(NhaCungCap ncc) throws Exception {
        requireQuanLy();
        return dao.update(ncc);
    }

    public boolean xoaNhaCungCap(String maNCC) throws Exception {
        requireQuanLy();
        // DAO đã check “còn phiếu CHODUYET” và throw IllegalStateException đúng nghiệp vụ
        return dao.softDelete(maNCC);
    }
}
