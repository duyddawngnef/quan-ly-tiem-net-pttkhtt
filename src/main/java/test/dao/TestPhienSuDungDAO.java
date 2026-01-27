package test.dao;

import dao.PhienSuDungDAO;
import entity.PhienSuDung;
import java.time.LocalDateTime;
import java.util.List;

public class TestPhienSuDungDAO {
    public static void main(String[] args) {
        PhienSuDungDAO dao = new PhienSuDungDAO();
        System.out.println("--- BẮT ĐẦU TEST PHIENSUDUNG DAO ---");

        // Biến lưu mã phiên vừa tạo để dùng cho các test sau
        String maPhienVuaTao = "";
        // 1. TEST SINH MÃ & LẤY DANH SÁCH
        try {
            System.out.println("\n[1] Kiểm tra dữ liệu chung:");
            System.out.println("- Mã phiên dự kiến tiếp theo: " + dao.generateMaPhien());

            List<PhienSuDung> list = dao.getAll();
            System.out.println("- Tổng số phiên trong CSDL: " + list.size());
        } catch (Exception e) {
            System.err.println("Lỗi phần 1: " + e.getMessage());
        }

        // 2. TEST MỞ PHIÊN MỚI (INSERT)
        try {
            System.out.println("\n[2] Test Mở Phiên Mới:");

            // GIẢ LẬP DỮ LIỆU ĐẦU VÀO
            // LƯU Ý: 'MAY001', 'NV001' phải ĐANG TỒN TẠI trong database của bạn
            PhienSuDung p = new PhienSuDung();
            p.setMamay("MAY001"); // Đảm bảo máy này chưa có ai ngồi
            p.setManv("NV001");
            p.setMakh("KH001");   // Có thể để null nếu là khách vãng lai
            p.setGiamoigio(10000.0);
            p.setGiobatdau(LocalDateTime.now());
            p.setLoaithanhtoan("TAIKHOAN");

            // Thực hiện insert
            boolean isInserted = dao.insert(p);

            if (isInserted) {
                maPhienVuaTao = p.getMaphien(); // Lấy mã phiên DAO vừa sinh ra (VD: PS015)
                System.out.println("=> THÀNH CÔNG: Đã mở phiên " + maPhienVuaTao + " trên máy " + p.getMamay());
            }
        } catch (Exception e) {
            System.err.println("=> THẤT BẠI (Có thể do Máy Bận hoặc Sai mã FK): " + e.getMessage());
        }

        // 3. TEST KIỂM TRA MÁY ĐANG CHẠY & GỌI DỊCH VỤ

        if (!maPhienVuaTao.isEmpty()) {
            try {
                System.out.println("\n[3] Test Update Dịch Vụ & Kiểm tra trạng thái:");

                // Kiểm tra máy có đang báo bận không
                PhienSuDung dangChay = dao.getPhienDangChay("MAY001");
                if (dangChay != null) {
                    System.out.println("- Máy MAY001 đang chạy phiên: " + dangChay.getMaphien());
                }

                // Giả lập khách gọi thêm nước ngọt (Cập nhật tiền dịch vụ)
                boolean updateDV = dao.updateTienDichVu(maPhienVuaTao, 20000.0);
                if (updateDV) {
                    System.out.println("- Đã cập nhật tiền dịch vụ lên 20,000đ cho phiên " + maPhienVuaTao);
                }

            } catch (Exception e) {
                System.err.println("Lỗi phần 3: " + e.getMessage());
            }
        }

        // 4. TEST KẾT THÚC PHIÊN (THANH TOÁN)

        if (!maPhienVuaTao.isEmpty()) {
            try {
                System.out.println("\n[4] Test Kết Thúc Phiên (Thanh Toán):");

                // Lấy thông tin phiên hiện tại để tính toán
                PhienSuDung phienCanDong = dao.getById(maPhienVuaTao);

                // Giả lập tính toán (Thực tế phần này do BUS làm)
                phienCanDong.setGioketthuc(LocalDateTime.now().plusHours(2)); // Giả vờ chơi 2 tiếng
                phienCanDong.setTonggio(2.0);
                phienCanDong.setTiengiochoi(20000.0); // 2h * 10k
                phienCanDong.setTongtien(20000.0 + phienCanDong.getTiendichvu()); // Tiền giờ + Dịch vụ

                boolean isEnded = dao.ketThucPhien(phienCanDong);
                if (isEnded) {
                    System.out.println("=> THÀNH CÔNG: Đã đóng phiên " + maPhienVuaTao);
                    System.out.println("   Tổng tiền thu: " + phienCanDong.getTongtien());
                }

            } catch (Exception e) {
                System.err.println("=> Lỗi kết thúc phiên: " + e.getMessage());
            }
        }

        System.out.println("\n--- KẾT THÚC TEST ---");
    }
}