package bus;

import entity.NhanVien;
import entity.KhachHang;

public class SessionManager {

    private static NhanVien currentUser;
    private static KhachHang currentKhachHang;

    private SessionManager() {}

    // ===== Nhân viên =====
    public static void setCurrentUser(NhanVien nv) {
        currentUser = nv;
        if (nv != null) currentKhachHang = null;
    }

    public static NhanVien getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
        currentKhachHang = null;
    }
}

