package test.dao;

import dao.HoaDonDAO;
import entity.HoaDon;
import java.time.LocalDateTime;
import java.util.List;

public class TestHoaDonDAO {

    private static HoaDonDAO dao = new HoaDonDAO();

    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU TEST HÓA ĐƠN DAO ===\n");

        // Test 1: Tạo mã tự động
        testTaoMaTuDong();

        // Test 2: Thêm hóa đơn
        testThem();

        // Test 3: Cập nhật hóa đơn
        testCapNhat();

        // Test 4: Tìm theo mã
        testTimTheoMa();

        // Test 5: Tìm theo phiên
        testTimTheoPhien();

        // Test 6: Tìm theo khách hàng
        testTimTheoKhachHang();

        // Test 7: Tìm theo nhân viên
        testTimTheoNhanVien();

        // Test 8: Tìm theo trạng thái
        testTimTheoTrangThai();

        // Test 9: Tìm theo khoảng thời gian
        testTimTheoKhoangThoiGian();

        // Test 10: Lấy tất cả
        testLayTatCa();

        // Test 11: Thanh toán hóa đơn
        testThanhToan();

        // Test 12: Thống kê doanh thu
        testTongDoanhThu();

        // Test 13: Thống kê doanh thu giờ chơi
        testTongDoanhThuGioChoi();

        // Test 14: Thống kê doanh thu dịch vụ
        testTongDoanhThuDichVu();

        // Test 15: Thống kê tổng giảm giá
        testTongGiamGia();

        // Test 16: Đếm hóa đơn theo trạng thái
        testDemHoaDonTheoTrangThai();

        // Test 17: Top khách hàng chi tiêu nhiều
        testTopKhachHangChiTieu();

        // Test 18: Doanh thu theo nhân viên
        testDoanhThuTheoNhanVien();

        // Test 19: Xóa hóa đơn
        testXoa();

        System.out.println("\n=== KẾT THÚC TEST ===");
    }

    // ===== TEST 1: TẠO MÃ TỰ ĐỘNG =====
    private static void testTaoMaTuDong() {
        System.out.println("--- Test 1: Tạo mã hóa đơn tự động ---");
        try {
            String maMoi = dao.taoMaHoaDonTuDong();
            System.out.println("✓ Mã hóa đơn mới: " + maMoi);
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 2: THÊM HÓA ĐƠN - ĐÃ SỬA =====
    private static void testThem() {
        System.out.println("--- Test 2: Thêm hóa đơn mới ---");
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(dao.taoMaHoaDonTuDong());
            hoaDon.setMaPhien("PS001");  // Sử dụng mã phiên có trong DB
            hoaDon.setMaKH("KH001");
            hoaDon.setMaNV("NV001");
            hoaDon.setNgayLap(LocalDateTime.now());
            hoaDon.setTienGioChoi(30000);
            hoaDon.setTienDichVu(25000);
            hoaDon.setTongTien(55000);
            hoaDon.setGiamGia(5000);
            hoaDon.setThanhToan(50000);
            hoaDon.setPhuongThucTT("TAIKHOAN");

            // SỬA: Đổi từ "CHUA" thành "CHUATHANHTOAN"
            hoaDon.setTrangThai("CHUATHANHTOAN");  // ← ĐÃ SỬA

            boolean ketQua = dao.them(hoaDon);

            if (ketQua) {
                System.out.println("✓ Thêm hóa đơn thành công!");
                System.out.println("  Mã hóa đơn: " + hoaDon.getMaHD());
                System.out.println("  Tiền giờ chơi: " + hoaDon.getTienGioChoiFormatted());
                System.out.println("  Tiền dịch vụ: " + hoaDon.getTienDichVuFormatted());
                System.out.println("  Tổng tiền: " + hoaDon.getTongTienFormatted());
                System.out.println("  Thanh toán: " + hoaDon.getThanhToanFormatted());
            } else {
                System.out.println("✗ Thêm hóa đơn thất bại!");
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 3: CẬP NHẬT HÓA ĐƠN =====
    private static void testCapNhat() {
        System.out.println("--- Test 3: Cập nhật hóa đơn ---");
        try {
            HoaDon hoaDon = dao.timTheoMa("HD001");

            if (hoaDon != null) {
                hoaDon.setGiamGia(10000);
                hoaDon.setThanhToan(hoaDon.getTongTien() - 10000);

                boolean ketQua = dao.capNhat(hoaDon);

                if (ketQua) {
                    System.out.println("✓ Cập nhật hóa đơn thành công!");
                    System.out.println("  Giảm giá mới: " + hoaDon.getGiamGiaFormatted());
                    System.out.println("  Thanh toán mới: " + hoaDon.getThanhToanFormatted());
                } else {
                    System.out.println("✗ Cập nhật hóa đơn thất bại!");
                }
            } else {
                System.out.println("✗ Không tìm thấy hóa đơn HD001");
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 4: TÌM THEO MÃ =====
    private static void testTimTheoMa() {
        System.out.println("--- Test 4: Tìm hóa đơn theo mã ---");
        try {
            String maHD = "HD001";
            HoaDon hoaDon = dao.timTheoMa(maHD);

            if (hoaDon != null) {
                System.out.println("✓ Tìm thấy hóa đơn!");
                inThongTinHoaDon(hoaDon);
            } else {
                System.out.println("✗ Không tìm thấy hóa đơn: " + maHD);
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 5: TÌM THEO PHIÊN - ĐÃ SỬA =====
    private static void testTimTheoPhien() {
        System.out.println("--- Test 5: Tìm hóa đơn theo phiên sử dụng ---");
        try {
            String maPhien = "PS001";  // ← SỬA: Dùng mã có trong DB
            HoaDon hoaDon = dao.timTheoPhien(maPhien);

            if (hoaDon != null) {
                System.out.println("✓ Tìm thấy hóa đơn của phiên " + maPhien);
                System.out.println("  Mã hóa đơn: " + hoaDon.getMaHD());
                System.out.println("  Tổng tiền: " + hoaDon.getTongTienFormatted());
            } else {
                System.out.println("✗ Không tìm thấy hóa đơn của phiên: " + maPhien);
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 6: TÌM THEO KHÁCH HÀNG =====
    private static void testTimTheoKhachHang() {
        System.out.println("--- Test 6: Tìm hóa đơn theo khách hàng ---");
        try {
            String maKH = "KH001";
            List<HoaDon> danhSach = dao.timTheoKhachHang(maKH);

            System.out.println("✓ Tìm thấy " + danhSach.size() + " hóa đơn của khách hàng " + maKH);

            if (!danhSach.isEmpty()) {
                double tongChi = danhSach.stream()
                        .filter(hd -> "DATHANHTOAN".equals(hd.getTrangThai()))
                        .mapToDouble(HoaDon::getThanhToan)
                        .sum();
                System.out.println("  Tổng chi tiêu: " + String.format("%,.0f VND", tongChi));
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 7: TÌM THEO NHÂN VIÊN =====
    private static void testTimTheoNhanVien() {
        System.out.println("--- Test 7: Tìm hóa đơn theo nhân viên ---");
        try {
            String maNV = "NV001";
            List<HoaDon> danhSach = dao.timTheoNhanVien(maNV);

            System.out.println("✓ Nhân viên " + maNV + " đã lập " + danhSach.size() + " hóa đơn");

            if (!danhSach.isEmpty()) {
                double tongDoanhThu = danhSach.stream()
                        .filter(hd -> "DATHANHTOAN".equals(hd.getTrangThai()))
                        .mapToDouble(HoaDon::getThanhToan)
                        .sum();
                System.out.println("  Doanh thu: " + String.format("%,.0f VND", tongDoanhThu));
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 8: TÌM THEO TRẠNG THÁI - ĐÃ SỬA =====
    private static void testTimTheoTrangThai() {
        System.out.println("--- Test 8: Tìm hóa đơn theo trạng thái ---");
        try {
            // SỬA: Dùng giá trị đúng
            String[] trangThai = {"CHUATHANHTOAN", "DATHANHTOAN"};  // ← ĐÃ SỬA

            for (String tt : trangThai) {
                List<HoaDon> danhSach = dao.timTheoTrangThai(tt);
                System.out.println("  " + tt + ": " + danhSach.size() + " hóa đơn");
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 9: TÌM THEO KHOẢNG THỜI GIAN =====
    private static void testTimTheoKhoangThoiGian() {
        System.out.println("--- Test 9: Tìm hóa đơn theo khoảng thời gian ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusDays(7);
            LocalDateTime denNgay = LocalDateTime.now();

            List<HoaDon> danhSach = dao.timTheoKhoangThoiGian(tuNgay, denNgay);

            System.out.println("✓ Tìm thấy " + danhSach.size() + " hóa đơn trong 7 ngày qua");

            if (!danhSach.isEmpty()) {
                long daThanhToan = danhSach.stream()
                        .filter(hd -> "DATHANHTOAN".equals(hd.getTrangThai()))
                        .count();
                System.out.println("  Đã thanh toán: " + daThanhToan);
                System.out.println("  Chưa thanh toán: " + (danhSach.size() - daThanhToan));
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 10: LẤY TẤT CẢ =====
    private static void testLayTatCa() {
        System.out.println("--- Test 10: Lấy tất cả hóa đơn ---");
        try {
            List<HoaDon> danhSach = dao.layTatCa();

            System.out.println("✓ Tổng số hóa đơn: " + danhSach.size());

            if (!danhSach.isEmpty()) {
                System.out.println("  Hóa đơn mới nhất:");
                HoaDon hdMoiNhat = danhSach.get(0);
                System.out.println("  - " + hdMoiNhat.getMaHD() +
                        " - " + hdMoiNhat.getThanhToanFormatted() +
                        " - " + hdMoiNhat.getTrangThai());
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 11: THANH TOÁN HÓA ĐƠN =====
    private static void testThanhToan() {
        System.out.println("--- Test 11: Thanh toán hóa đơn ---");
        try {
            String maHD = "HD001";
            boolean ketQua = dao.thanhToan(maHD, "TIENMAT");

            if (ketQua) {
                System.out.println("✓ Thanh toán hóa đơn " + maHD + " thành công!");
                HoaDon hd = dao.timTheoMa(maHD);
                if (hd != null) {
                    System.out.println("  Trạng thái: " + hd.getTrangThai());
                    System.out.println("  Phương thức: " + hd.getPhuongThucTT());
                }
            } else {
                System.out.println("✗ Thanh toán hóa đơn thất bại!");
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 12: TỔNG DOANH THU =====
    private static void testTongDoanhThu() {
        System.out.println("--- Test 12: Thống kê tổng doanh thu ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            double tongDoanhThu = dao.tongDoanhThu(tuNgay, denNgay);

            System.out.println("✓ Tổng doanh thu trong 1 tháng qua: " +
                    String.format("%,.0f VND", tongDoanhThu));
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 13: DOANH THU GIỜ CHƠI =====
    private static void testTongDoanhThuGioChoi() {
        System.out.println("--- Test 13: Doanh thu từ giờ chơi ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            double tongGioChoi = dao.tongDoanhThuGioChoi(tuNgay, denNgay);

            System.out.println("✓ Doanh thu giờ chơi: " +
                    String.format("%,.0f VND", tongGioChoi));
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 14: DOANH THU DỊCH VỤ =====
    private static void testTongDoanhThuDichVu() {
        System.out.println("--- Test 14: Doanh thu từ dịch vụ ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            double tongDichVu = dao.tongDoanhThuDichVu(tuNgay, denNgay);

            System.out.println("✓ Doanh thu dịch vụ: " +
                    String.format("%,.0f VND", tongDichVu));
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 15: TỔNG GIẢM GIÁ =====
    private static void testTongGiamGia() {
        System.out.println("--- Test 15: Tổng giảm giá đã áp dụng ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            double tongGiamGia = dao.tongGiamGia(tuNgay, denNgay);

            System.out.println("✓ Tổng giảm giá: " +
                    String.format("%,.0f VND", tongGiamGia));
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 16: ĐẾM HÓA ĐƠN THEO TRẠNG THÁI - ĐÃ SỬA =====
    private static void testDemHoaDonTheoTrangThai() {
        System.out.println("--- Test 16: Đếm hóa đơn theo trạng thái ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            // SỬA: Dùng giá trị đúng
            int chuaTT = dao.demHoaDonTheoTrangThai("CHUATHANHTOAN", tuNgay, denNgay);  // ← ĐÃ SỬA
            int daTT = dao.demHoaDonTheoTrangThai("DATHANHTOAN", tuNgay, denNgay);

            System.out.println("  Chưa thanh toán: " + chuaTT);
            System.out.println("  Đã thanh toán: " + daTT);
            System.out.println("  Tổng: " + (chuaTT + daTT));
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 17: TOP KHÁCH HÀNG CHI TIÊU =====
    private static void testTopKhachHangChiTieu() {
        System.out.println("--- Test 17: Top khách hàng chi tiêu nhiều nhất ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            List<Object[]> top = dao.topKhachHangChiTieu(10, tuNgay, denNgay);

            System.out.println("✓ Top 10 khách hàng chi tiêu nhiều:");
            for (int i = 0; i < top.size(); i++) {
                Object[] row = top.get(i);
                System.out.println("  " + (i+1) + ". " + row[0] + " - " +
                        row[1] + " " + row[2] + ": " +
                        String.format("%,.0f VND", (double)row[3]));
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 18: DOANH THU THEO NHÂN VIÊN =====
    private static void testDoanhThuTheoNhanVien() {
        System.out.println("--- Test 18: Doanh thu theo nhân viên ---");
        try {
            LocalDateTime tuNgay = LocalDateTime.now().minusMonths(1);
            LocalDateTime denNgay = LocalDateTime.now();

            List<Object[]> danhSach = dao.doanhThuTheoNhanVien(tuNgay, denNgay);

            System.out.println("✓ Doanh thu theo nhân viên:");
            for (Object[] row : danhSach) {
                System.out.println("  " + row[0] + " - " + row[1] + " " + row[2] + ":");
                System.out.println("    Số hóa đơn: " + row[3]);
                System.out.println("    Doanh thu: " + String.format("%,.0f VND", (double)row[4]));
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== TEST 19: XÓA HÓA ĐƠN - ĐÃ SỬA =====
    private static void testXoa() {
        System.out.println("--- Test 19: Xóa hóa đơn ---");
        try {
            // Tạo hóa đơn test để xóa
            HoaDon hdTest = new HoaDon();
            hdTest.setMaHD("HD_TEST_DELETE");
            hdTest.setMaPhien("PS001");  // Dùng phiên có trong DB
            hdTest.setMaKH("KH001");
            hdTest.setMaNV("NV001");
            hdTest.setNgayLap(LocalDateTime.now());
            hdTest.setTienGioChoi(10000);
            hdTest.setTienDichVu(5000);
            hdTest.setTongTien(15000);
            hdTest.setGiamGia(0);
            hdTest.setThanhToan(15000);
            hdTest.setPhuongThucTT("TIENMAT");

            // SỬA: Dùng giá trị đúng
            hdTest.setTrangThai("CHUATHANHTOAN");  // ← ĐÃ SỬA

            dao.them(hdTest);

            // Xóa hóa đơn
            boolean ketQua = dao.xoa("HD_TEST_DELETE");

            if (ketQua) {
                System.out.println("✓ Xóa hóa đơn thành công!");
            } else {
                System.out.println("✗ Xóa hóa đơn thất bại!");
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    // ===== PHƯƠNG THỨC HỖ TRỢ =====
    private static void inThongTinHoaDon(HoaDon hd) {
        System.out.println("  Mã hóa đơn: " + hd.getMaHD());
        System.out.println("  Mã phiên: " + hd.getMaPhien());
        System.out.println("  Khách hàng: " + hd.getMaKH());
        System.out.println("  Nhân viên: " + hd.getMaNV());
        System.out.println("  Ngày lập: " + hd.getNgayLapFormatted());
        System.out.println("  Tiền giờ chơi: " + hd.getTienGioChoiFormatted());
        System.out.println("  Tiền dịch vụ: " + hd.getTienDichVuFormatted());
        System.out.println("  Tổng tiền: " + hd.getTongTienFormatted());
        System.out.println("  Giảm giá: " + hd.getGiamGiaFormatted());
        System.out.println("  Thanh toán: " + hd.getThanhToanFormatted());
        System.out.println("  Phương thức: " + hd.getPhuongThucTT());
        System.out.println("  Trạng thái: " + hd.getTrangThai());
    }
}