package bus;

import dao.ThongkeDAO;
import entity.NhanVien;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongKeBUS {

    private final ThongkeDAO thongkeDAO = new ThongkeDAO();

    // ===== Helpers phân quyền =====
    private void requireQuanLy() throws Exception {
        NhanVien current = SessionManager.getCurrentUser(); // <-- nếu nhóm bạn đặt tên khác, sửa dòng này
        if (current == null) throw new Exception("Chưa đăng nhập");
        if (!"QUANLY".equalsIgnoreCase(current.getChucvu())) {
            throw new Exception("Không có quyền thực hiện");
        }
    }

    private void requireQuanLyOrNhanVien() throws Exception {
        NhanVien current = SessionManager.getCurrentUser();
        if (current == null) throw new Exception("Chưa đăng nhập");

        String cv = current.getChucvu();
        if (cv == null) throw new Exception("Không có quyền thực hiện");

        cv = cv.toUpperCase();
        if (!cv.equals("QUANLY") && !cv.equals("NHANVIEN")) {
            throw new Exception("Không có quyền thực hiện");
        }
    }

    // ===== 20.2 THỐNG KÊ DOANH THU (Map) =====
    public Map<String, Object> thongKeDoanhThu(LocalDate tuNgay, LocalDate denNgay) throws Exception {
        requireQuanLy();

        if (tuNgay == null || denNgay == null) throw new Exception("Ngày thống kê không hợp lệ");
        if (tuNgay.isAfter(denNgay)) throw new Exception("Từ ngày phải nhỏ hơn hoặc bằng đến ngày");

        Map<String, Object> hoaDonPart = thongkeDAO.thongKeDoanhThuTongHop(tuNgay, denNgay);
        double tongDoanhThu = (double) hoaDonPart.get("TongDoanhThu");
        double tongTienGioChoi = (double) hoaDonPart.get("TongTienGioChoi");
        double tongTienDichVu = (double) hoaDonPart.get("TongTienDichVu");
        int soHoaDon = (int) hoaDonPart.get("SoHoaDon");

        long soNgay = ChronoUnit.DAYS.between(tuNgay, denNgay) + 1;
        double doanhThuTB = (soNgay <= 0) ? 0.0 : (tongDoanhThu / soNgay);

        double tongNhapHang = thongkeDAO.tongNhapHang(tuNgay, denNgay);
        double loiNhuan = tongDoanhThu - tongNhapHang;

        Map<String, Object> result = new HashMap<>();
        result.put("TuNgay", tuNgay);
        result.put("DenNgay", denNgay);

        result.put("TongDoanhThu", tongDoanhThu);
        result.put("TongTienGioChoi", tongTienGioChoi);
        result.put("TongTienDichVu", tongTienDichVu);

        result.put("TongNhapHang", tongNhapHang);
        result.put("LoiNhuan", loiNhuan);

        result.put("SoHoaDon", soHoaDon);
        result.put("SoNgay", (int) soNgay);
        result.put("DoanhThuTrungBinh", doanhThuTB);

        return result;
    }

    // ===== 20.1 Thống kê doanh thu theo tháng =====
    public Map<String, Object> thongKeDoanhThuTheoThang(int thang, int nam) throws Exception {
        requireQuanLy();

        if (thang < 1 || thang > 12) throw new Exception("Tháng không hợp lệ");
        if (nam < 2000) throw new Exception("Năm không hợp lệ");

        YearMonth ym = YearMonth.of(nam, thang);
        LocalDate tu = ym.atDay(1);
        LocalDate den = ym.atEndOfMonth();

        return thongKeDoanhThu(tu, den);
    }

    // ===== 20.3 Top dịch vụ bán chạy =====
    public List<Map<String, Object>> thongKeDichVuBanChay(LocalDate tuNgay, LocalDate denNgay, int top) throws Exception {
        requireQuanLy();

        if (tuNgay == null || denNgay == null) throw new Exception("Ngày thống kê không hợp lệ");
        if (tuNgay.isAfter(denNgay)) throw new Exception("Từ ngày phải nhỏ hơn hoặc bằng đến ngày");

        return thongkeDAO.thongKeDichVuBanChay(tuNgay, denNgay, top);
    }

    // ===== 20.4 Tổng quan =====
    public Map<String, Object> thongKeTongQuan() throws Exception {
        // tài liệu nói QUANLY/NHANVIEN được xem
        requireQuanLyOrNhanVien();
        return thongkeDAO.thongKeTongQuan();
    }

    // (Tuỳ chọn) Export Excel theo tài liệu - bạn muốn mình viết sau cũng được
    public void xuatBaoCaoExcel(LocalDate tuNgay, LocalDate denNgay) {
        throw new UnsupportedOperationException("Chưa implement (tuỳ chọn).");
    }
}
