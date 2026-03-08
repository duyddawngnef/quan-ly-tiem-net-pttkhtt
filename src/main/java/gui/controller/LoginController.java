package gui.controller;

import bus.KhachHangBUS;
import bus.NhanVienBUS;
import entity.KhachHang;
import entity.NhanVien;
import utils.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // Đã đổi ID tiếng Việt
    @FXML private TextField txtTenDangNhap;
    @FXML private PasswordField pfMatKhau;
    @FXML private ComboBox<String> cbLoaiTaiKhoan;
    @FXML private Label lblThongBao;
    @FXML private Button btnDangNhap;

    private KhachHangBUS khachHangBUS = new KhachHangBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(cbLoaiTaiKhoan != null) {
            cbLoaiTaiKhoan.getItems().addAll("Nhân viên", "Khách hàng");
            cbLoaiTaiKhoan.getSelectionModel().selectFirst();
        }
        if(lblThongBao != null) lblThongBao.setText("");
    }

    @FXML
    private void handleDangNhap() {
        String username = txtTenDangNhap.getText();
        String password = pfMatKhau.getText();
        String role = cbLoaiTaiKhoan.getValue();

        try {
            if ("Khách hàng".equals(role)) {
                KhachHang kh = khachHangBUS.dangNhap(username, password);
                if (kh != null) {
                    SessionManager.setCurrentUser(kh); // Quan trọng
                    chuyenHuongMain("Khách hàng");
                }
            } else {
                NhanVien nv = nhanVienBUS.dangNhap(username, password);
                if (nv != null) {
                    SessionManager.setCurrentUser(nv); // Quan trọng
                    chuyenHuongMain("Nhân viên");
                }
            }
        } catch (Exception e) {
            lblThongBao.setText(e.getMessage());
            lblThongBao.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void chuyenSangDangKy() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/fxml/register.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Đăng ký tài khoản");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chuyenHuongMain(String role) throws Exception {
        Stage currentStage = (Stage) txtTenDangNhap.getScene().getWindow();
        currentStage.close();

        // Đã sửa file name thành chuẩn docx
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/fxml/main.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Hệ Thống Quản Lý Tiệm Net - " + role);
        stage.setScene(new Scene(root, 1200, 800));
        stage.setMaximized(true);
        stage.show();
    }
}