package gui.controller;

import entity.NhanVien;
import entity.KhachHang;
import utils.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // Thông tin người dùng (Đã khớp ID với file main.fxml)
    @FXML private Label lblAvatarChar;
    @FXML private Label lblUserName;
    @FXML private Label lblUserRole;

    // Khu vực Header và Nội dung
    @FXML private Label lblPageTitle;
    @FXML private Label lblCurrentTime;
    @FXML private StackPane contentPane;

    // Danh sách nút Menu ở Sidebar
    @FXML private Button btnSoDoMay, btnPhienSuDung, btnNapTien, btnHoaDon;
    @FXML private Button btnDichVu, btnGoiDichVu, btnKhuyenMai, btnNhapHang;
    @FXML private Button btnKhachHang, btnNhanVien, btnMayTinh, btnKhuMay, btnThongKe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            updateHeader();
            setupMenuByRole();
            loadSoDoMay(); // Gọi hàm hiển thị trang mặc định
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo màn hình Main: " + e.getMessage());
        }
    }

    private void updateHeader() {
        if (SessionManager.isNhanVien() || SessionManager.isQuanLy()) {
            NhanVien nv = SessionManager.getCurrentNhanVien();
            if (nv != null) {
                if (lblUserName != null) lblUserName.setText(nv.getHo() + " " + nv.getTen());
                if (lblUserRole != null) lblUserRole.setText(nv.getChucvu());
                // Lấy chữ cái đầu tiên của Tên làm Avatar
                if (lblAvatarChar != null && nv.getTen() != null && !nv.getTen().isEmpty()) {
                    lblAvatarChar.setText(String.valueOf(nv.getTen().charAt(0)).toUpperCase());
                }
            }
        } else if (SessionManager.isKhachHang()) {
            KhachHang kh = SessionManager.getCurrentKhachHang();
            if (kh != null) {
                if (lblUserName != null) lblUserName.setText(kh.getHo() + " " + kh.getTen());
                if (lblUserRole != null) lblUserRole.setText("KHACHHANG");
                // Lấy chữ cái đầu tiên của Tên làm Avatar
                if (lblAvatarChar != null && kh.getTen() != null && !kh.getTen().isEmpty()) {
                    lblAvatarChar.setText(String.valueOf(kh.getTen().charAt(0)).toUpperCase());
                }
            }
        }
        if (lblCurrentTime != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            lblCurrentTime.setText(dtf.format(LocalDateTime.now()));
        }
    }

    private void setupMenuByRole() {
        if (!SessionManager.isQuanLy()) {
            if(btnNhanVien != null) { btnNhanVien.setVisible(false); btnNhanVien.setManaged(false); }
            if(btnThongKe != null) { btnThongKe.setVisible(false); btnThongKe.setManaged(false); }
            if(btnNhapHang != null) { btnNhapHang.setVisible(false); btnNhapHang.setManaged(false); }
            if(btnKhuyenMai != null) { btnKhuyenMai.setVisible(false); btnKhuyenMai.setManaged(false); }
        }
        if (SessionManager.isKhachHang()) {
            if(btnMayTinh != null) { btnMayTinh.setVisible(false); btnMayTinh.setManaged(false); }
            if(btnKhuMay != null) { btnKhuMay.setVisible(false); btnKhuMay.setManaged(false); }
            if(btnKhachHang != null) { btnKhachHang.setVisible(false); btnKhachHang.setManaged(false); }
        }
    }

    private void loadView(String fxmlPath, String title) {
        try {
            if (contentPane == null) return;

            URL fileUrl = getClass().getResource("/fxml/" + fxmlPath);
            if (fileUrl == null) {
                System.err.println("⚠️ Cảnh báo: Không tìm thấy file /fxml/" + fxmlPath + " -> Bỏ qua tải giao diện này để tránh sập app.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fileUrl);
            Node node = loader.load();
            contentPane.getChildren().setAll(node);
            if(lblPageTitle != null) lblPageTitle.setText(title.toUpperCase());
        } catch (Exception e) {
            System.err.println("⛔ Lỗi khi load file " + fxmlPath + ": " + e.getMessage());
        }
    }

    // --- CÁC HÀM XỬ LÝ CLICK MENU (Đã đổi tên theo file FXML của bạn) ---
    @FXML public void loadSoDoMay() { loadView("sodoMay.fxml", "Sơ đồ máy"); }
    @FXML public void loadPhienSuDung() { loadView("phienSuDung.fxml", "Quản lý phiên sử dụng"); }
    @FXML public void loadNapTien() { loadView("napTien.fxml", "Nạp tiền"); }
    @FXML public void loadHoaDon() { loadView("hoaDon.fxml", "Quản lý hóa đơn"); }
    @FXML public void loadDichVu() { loadView("dichVu.fxml", "Quản lý dịch vụ"); }
    @FXML public void loadGoiDichVu() { loadView("goiDichVu.fxml", "Quản lý gói dịch vụ"); }
    @FXML public void loadKhuyenMai() { loadView("khuyenMai.fxml", "Chương trình khuyến mãi"); }
    @FXML public void loadNhapHang() { loadView("nhapHang.fxml", "Nhập hàng"); }
    @FXML public void loadKhachHang() { loadView("khachHang.fxml", "Quản lý khách hàng"); }
    @FXML public void loadNhanVien() { loadView("nhanVien.fxml", "Quản lý nhân viên"); }
    @FXML public void loadMayTinh() { loadView("mayTinh.fxml", "Quản lý máy tính"); }
    @FXML public void loadKhuMay() { loadView("khuMay.fxml", "Quản lý khu máy"); }
    @FXML public void loadThongKe() { loadView("thongKe.fxml", "Báo cáo thống kê"); }

    @FXML
    private void handleLogout() {
        SessionManager.clearSession();
        try {
            Stage currentStage = (Stage) btnSoDoMay.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Đăng nhập");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}