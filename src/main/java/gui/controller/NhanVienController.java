package gui.controller;

import bus.NhanVienBUS;
import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NhanVienController implements Initializable {

    @FXML private TextField txtSearch;
    @FXML private ComboBox<String> cmbChucVu;
    @FXML private Button btnSua;
    @FXML private Button btnXoa;

    @FXML private TableView<NhanVien> tableNhanVien;
    @FXML private TableColumn<NhanVien, String> colMaNV, colHoTen, colChucVu, colTrangThai;

    // Form bên phải
    @FXML private Label lblFormTitle;
    @FXML private TextField txtMaNV, txtHoTen, txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbChucVuForm;

    private final NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private final ObservableList<NhanVien> listNhanVien = FXCollections.observableArrayList();
    private boolean isInsertMode = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        cmbChucVu.getItems().addAll("TATC", "QUANLY", "NHANVIEN", "THUNGAN");
        cmbChucVu.getSelectionModel().selectFirst();

        cmbChucVuForm.getItems().addAll("QUANLY", "NHANVIEN", "THUNGAN");

        loadData();
    }

    private void setupTable() {
        colMaNV.setCellValueFactory(new PropertyValueFactory<>("manv"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("ten")); // Có thể update ghép tên sau
        colChucVu.setCellValueFactory(new PropertyValueFactory<>("chucvu"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangthai"));
    }

    private void loadData() {
        try {
            List<NhanVien> list = nhanVienBUS.getAllNhanVienDangLamViec();
            listNhanVien.setAll(list);
            tableNhanVien.setItems(listNhanVien);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi tải dữ liệu", e.getMessage());
        }
    }

    @FXML
    private void handleSearch() {
        try {
            List<NhanVien> list = nhanVienBUS.timKiemNhanVien(txtSearch.getText());
            listNhanVien.setAll(list);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi tìm kiếm", e.getMessage());
        }
    }

    @FXML
    private void handleRowSelect() {
        NhanVien selected = tableNhanVien.getSelectionModel().getSelectedItem();
        if (selected != null) {
            isInsertMode = false;
            lblFormTitle.setText("CẬP NHẬT NHÂN VIÊN");
            txtMaNV.setText(selected.getManv());
            txtHoTen.setText(selected.getHo() + " " + selected.getTen());
            txtUsername.setText(selected.getTendangnhap());
            txtPassword.setDisable(true); // Không cho sửa pass ở form này
            cmbChucVuForm.setValue(selected.getChucvu());
        }
    }

    @FXML
    private void handleThem() {
        isInsertMode = true;
        lblFormTitle.setText("THÊM NHÂN VIÊN");
        handleCancel(); // Xóa sạch form
        txtPassword.setDisable(false);
    }

    @FXML
    private void handleSua() {
        if (tableNhanVien.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn nhân viên cần sửa!");
        } else {
            txtHoTen.requestFocus(); // Focus con trỏ vào form bên phải
        }
    }

    @FXML
    private void handleXoa() {
        NhanVien selected = tableNhanVien.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn nhân viên cần xóa!");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Cho nhân viên này nghỉ việc?", ButtonType.YES, ButtonType.NO);
        if (confirm.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            try {
                if (nhanVienBUS.xoaNhanVien(selected.getManv())) {
                    showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đã khóa nhân viên thành công!");
                    loadData();
                    handleCancel();
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi xóa", e.getMessage());
            }
        }
    }

    @FXML
    private void handleSave() {
        try {
            NhanVien nv = new NhanVien();
            // Tách tạm Họ và Tên (Giả sử từ cuối là Tên, còn lại là Họ)
            String[] parts = txtHoTen.getText().trim().split(" ");
            if(parts.length > 0) {
                nv.setTen(parts[parts.length - 1]);
                nv.setHo(txtHoTen.getText().replace(nv.getTen(), "").trim());
            }

            nv.setChucvu(cmbChucVuForm.getValue());
            nv.setTendangnhap(txtUsername.getText());

            if (isInsertMode) {
                nv.setMatkhau(txtPassword.getText());
                nhanVienBUS.themNhanVien(nv);
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thêm nhân viên mới thành công!");
            } else {
                nv.setManv(txtMaNV.getText());
                nhanVienBUS.suaNhanVien(nv);
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Cập nhật thông tin thành công!");
            }

            loadData();
            handleCancel();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi lưu dữ liệu", e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        txtMaNV.clear();
        txtHoTen.clear();
        txtUsername.clear();
        txtPassword.clear();
        cmbChucVuForm.getSelectionModel().clearSelection();
        tableNhanVien.getSelectionModel().clearSelection();
        isInsertMode = true;
        lblFormTitle.setText("THÊM NHÂN VIÊN");
        txtPassword.setDisable(false);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}