package gui.controller;

import bus.NhanVienBUS;
import entity.NhanVien;
import gui.dialog.ThemNhanVienDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NhanVienController implements Initializable {

    @FXML private TextField txtTimKiem; // Đã đổi ID theo chuẩn tiếng Việt
    @FXML private ComboBox<String> cmbChucVu;
    @FXML private Button btnSua;
    @FXML private Button btnXoa;

    @FXML private TableView<NhanVien> tableNhanVien;
    @FXML private TableColumn<NhanVien, String> colMaNV, colHoTen, colChucVu, colTrangThai;

    private final NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private final ObservableList<NhanVien> listNhanVien = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        if (cmbChucVu != null) {
            cmbChucVu.getItems().addAll("TATC", "QUANLY", "NHANVIEN", "THUNGAN");
            cmbChucVu.getSelectionModel().selectFirst();
        }
        loadData();
    }

    private void setupTable() {
        if (colMaNV != null) colMaNV.setCellValueFactory(new PropertyValueFactory<>("manv"));
        if (colHoTen != null) colHoTen.setCellValueFactory(new PropertyValueFactory<>("ten"));
        if (colChucVu != null) colChucVu.setCellValueFactory(new PropertyValueFactory<>("chucvu"));
        if (colTrangThai != null) colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangthai"));
    }

    public void loadData() {
        try {
            List<NhanVien> list = nhanVienBUS.getAllNhanVienDangLamViec();
            listNhanVien.setAll(list);
            if (tableNhanVien != null) tableNhanVien.setItems(listNhanVien);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi tải dữ liệu", e.getMessage());
        }
    }

    @FXML
    private void handleSearch() {
        try {
            String keyword = txtTimKiem != null ? txtTimKiem.getText() : "";
            List<NhanVien> list = nhanVienBUS.timKiemNhanVien(keyword);
            listNhanVien.setAll(list);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi tìm kiếm", e.getMessage());
        }
    }

    @FXML
    private void handleThem() {
        openDialog(null); // Truyền null để báo là thêm mới
    }

    @FXML
    private void handleSua() {
        if (tableNhanVien == null) return;
        NhanVien selected = tableNhanVien.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn nhân viên cần sửa!");
            return;
        }
        openDialog(selected); // Truyền đối tượng đã chọn để sửa
    }

    @FXML
    private void handleXoa() {
        if (tableNhanVien == null) return;
        NhanVien selected = tableNhanVien.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Khóa tài khoản nhân viên này?", ButtonType.YES, ButtonType.NO);
        if (confirm.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            try {
                if (nhanVienBUS.xoaNhanVien(selected.getManv())) {
                    showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đã khóa nhân viên thành công!");
                    loadData();
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi xóa", e.getMessage());
            }
        }
    }

    private void openDialog(NhanVien nv) {
        try {
            // Đảm bảo file FXML Dialog đặt đúng vị trí này
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/fxml/dialogs/themNhanVien.fxml"));
            Parent root = loader.load();

            ThemNhanVienDialog ctrl = loader.getController();
            ctrl.setEntity(nv);
            ctrl.setOnSaveCallback(this::loadData); // Callback xịn của bạn

            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            if (tableNhanVien != null && tableNhanVien.getScene() != null) {
                stage.initOwner(tableNhanVien.getScene().getWindow());
            }
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Lỗi mở form", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}