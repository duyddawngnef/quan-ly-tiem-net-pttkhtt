package gui.controller;

import bus.KhachHangBUS;
import bus.MayTinhBUS;
import bus.KhuMayBUS;
import bus.PhienSuDungBUS;
import dao.PhienSuDungDAO;
import dao.MayTinhDAO;
import dao.KhachHangDAO;
import dao.GoiDichVuKhachHangDAO;
import dao.SuDungDichVuDAO;
import dao.HoaDonDAO;
import entity.KhachHang;
import entity.MayTinh;
import entity.KhuMay;
import entity.PhienSuDung;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utils.ThongBaoDialogHelper;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SoDoMayController implements Initializable {

    @FXML private VBox vboxKhuMay;
    @FXML private ComboBox<String> cboKhu;
    @FXML private Label lblMayTrong;
    @FXML private Label lblMayDangDung;
    @FXML private Label lblMayBaoTri;
    @FXML private Label lblTongMay;
    @FXML private Label lblSelectedMay;
    @FXML private Label lblAutoRefresh;
    @FXML private Button btnMoPhien;
    @FXML private Button btnKetThuc;

    private final MayTinhBUS mayTinhBUS = new MayTinhBUS();
    private final KhuMayBUS khuMayBUS = new KhuMayBUS();
    private final KhachHangBUS khachHangBUS= new KhachHangBUS();
    private final PhienSuDungBUS phienBUS = new PhienSuDungBUS(
        new PhienSuDungDAO(), new MayTinhDAO(), new KhachHangDAO(),
        new GoiDichVuKhachHangDAO(), new SuDungDichVuDAO(), new HoaDonDAO());
    private MayTinh selectedMay = null;
    private Timeline autoRefresh;
    private int refreshCountdown = 30;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadKhuCombo();
        loadSoDo();
        startAutoRefresh();
    }

    private void loadKhuCombo() {
        try {
            List<KhuMay> khuList = khuMayBUS.getAllKhuMay();
            cboKhu.getItems().clear();
            cboKhu.getItems().add("Tất cả");
            khuList.forEach(k -> cboKhu.getItems().add(k.getTenkhu()));
            cboKhu.setValue("Tất cả");
        } catch (Exception e) {
            cboKhu.getItems().setAll("Tất cả");
        }
    }

    public void loadSoDo() {
        try {
            List<MayTinh> allMay = mayTinhBUS.getAllMayTinh();
            List<KhuMay> allKhu = khuMayBUS.getAllKhuMay();
            updateStats(allMay);
            renderSoDo(allMay, allKhu);
        } catch (Exception e) {
            vboxKhuMay.getChildren().clear();
            Label errLbl = new Label("⚠  Lỗi tải dữ liệu: " + e.getMessage());
            errLbl.setStyle("-fx-text-fill:#C62828;");
            vboxKhuMay.getChildren().add(errLbl);
        }
    }

    private void updateStats(List<MayTinh> list) {
        long trong    = list.stream().filter(m -> "TRONG".equals(m.getTrangthai())).count();
        long dangDung = list.stream().filter(m -> "DANGDUNG".equals(m.getTrangthai())).count();
        long baoTri   = list.stream().filter(m -> "BAOTRI".equals(m.getTrangthai())).count();
        lblMayTrong.setText(String.valueOf(trong));
        lblMayDangDung.setText(String.valueOf(dangDung));
        lblMayBaoTri.setText(String.valueOf(baoTri));
        lblTongMay.setText(String.valueOf(list.size()));
    }

    private void renderSoDo(List<MayTinh> allMay, List<KhuMay> allKhu) {
        String filterKhu = cboKhu.getValue();
        vboxKhuMay.getChildren().clear();

        // Group by khu
        Map<String, List<MayTinh>> grouped = allMay.stream()
            .collect(Collectors.groupingBy(m -> m.getMakhu() == null ? "none" : m.getMakhu()));

        for (KhuMay khu : allKhu) {
            if (!"Tất cả".equals(filterKhu) && !khu.getTenkhu().equals(filterKhu)) continue;

            List<MayTinh> mayInKhu = grouped.getOrDefault(khu.getMakhu(), List.of());

            VBox khuBox = new VBox(10);
            khuBox.setStyle("-fx-background-color:#F8FAFF; -fx-background-radius:10; -fx-padding:16;");

            Label khuLabel = new Label("📍 " + khu.getTenkhu() +
                    "  (" + mayInKhu.size() + " máy)");
            khuLabel.setStyle("-fx-font-size:14px; -fx-font-weight:bold; -fx-text-fill:#1565C0;");
            khuBox.getChildren().add(khuLabel);

            FlowPane flowPane = new FlowPane(12, 12);
            flowPane.setPadding(new Insets(8, 0, 0, 0));

            for (MayTinh may : mayInKhu) {
                VBox card = buildMachineCard(may);
                flowPane.getChildren().add(card);
            }

            khuBox.getChildren().add(flowPane);
            vboxKhuMay.getChildren().add(khuBox);
        }
    }

    private VBox buildMachineCard(MayTinh may) {
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(115);
        card.setMaxWidth(115);
        card.setPrefHeight(95);
        card.setCursor(javafx.scene.Cursor.HAND);

        // Màu nền theo trạng thái - không có border
        String bgColor = switch (may.getTrangthai()) {
            case "TRONG"    -> "#E8F5E9";  // xanh lá nhạt
            case "DANGDUNG" -> "#E3F2FD";  // xanh dương nhạt
            case "BAOTRI"   -> "#FFF3E0";  // cam nhạt
            default          -> "#F5F5F5";  // xám nhạt
        };
        card.setStyle("-fx-background-color:" + bgColor + ";" +
                      "-fx-background-radius:8;" +
                      "-fx-border-color:transparent;" +
                      "-fx-border-width:0;");

        String icon = switch (may.getTrangthai()) {
            case "TRONG"    -> "💚";
            case "DANGDUNG" -> "💙";
            case "BAOTRI"   -> "🔧";
            default          -> "⚫";
        };

        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-font-size:22px;");
        Label nameLbl = new Label(may.getTenmay());
        nameLbl.getStyleClass().add("machine-name");
        nameLbl.setStyle("-fx-font-size:12px; -fx-font-weight:bold; -fx-text-fill:#212121;");
        Label statusLbl = new Label(may.getTrangthai());
        statusLbl.setStyle("-fx-font-size:10px; -fx-text-fill:#555555;");

        card.getChildren().addAll(iconLbl, nameLbl, statusLbl);
        card.setOnMouseClicked(e -> handleMachineClick(may, card));

        return card;
    }

    private void handleMachineClick(MayTinh may, VBox card) {
        selectedMay = may;
        lblSelectedMay.setText("Đã chọn: " + may.getTenmay() + " | Trạng thái: " + may.getTrangthai());

        boolean isTrong    = "TRONG".equals(may.getTrangthai());
        boolean isDangDung = "DANGDUNG".equals(may.getTrangthai());
        btnMoPhien.setDisable(!isTrong);
        btnKetThuc.setDisable(!isDangDung);
    }

    @FXML
    public void handleFilterKhu() { loadSoDo(); }

    @FXML
    public void handleRefresh() {
        selectedMay = null;
        lblSelectedMay.setText("Chưa chọn máy");
        btnMoPhien.setDisable(true);
        btnKetThuc.setDisable(true);
        loadSoDo();
    }

    @FXML
    public void handleMoPhien() {
        if (selectedMay == null) return;
        List<KhachHang> dskh;
        try{
            dskh = khachHangBUS.getAllKhachHang().stream().filter(e ->
                    "HOATDONG".equals(e.getTrangthai())).toList();
                    //.toList() -> ds khong the sua duoc
                    // .collect(Collector.toList())
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(dskh.isEmpty()){
            ThongBaoDialogHelper.showError(vboxKhuMay.getScene(), "Không có khách hàng nào đang hoạt động .");
        }
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Mở phiên mới");
//        dialog.setHeaderText("Máy: " + selectedMay.getTenmay());
//        dialog.setContentText("Nhập mã khách hàng:");
//        dialog.showAndWait().ifPresent(maKH -> {
//            if (maKH.trim().isEmpty()) return;
//            try {
//                phienBUS.moPhienMoi(maKH.trim(), selectedMay.getMamay());
//                // Refresh
//                selectedMay = null;
//                btnMoPhien.setDisable(true);
//                btnKetThuc.setDisable(true);
//                lblSelectedMay.setText("Chưa chọn máy");
//                loadSoDo();
//            } catch (Exception e) {
//                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
//                    javafx.scene.control.Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Lỗi mở phiên: " + e.getMessage());
//                alert.showAndWait();
//            }
//        });
            Stage dialog = makeDialog();
            ComboBox<KhachHang> cbKh = new ComboBox<>();
            cbKh.getItems().setAll(dskh);
            cbKh.setMaxWidth(Double.MAX_VALUE);
            cbKh.setPromptText("--Chọn khách hàng--");
            cbKh.setCellFactory(e -> khachHangCell());
            cbKh.setButtonCell(khachHangCell());

        Label  lblLoi = errLabel();
        Button btnOk  = primaryBtn("✔  Mở phiên", "#1565C0");
        Button btnHuy = secondaryBtn();
        btnHuy.setOnAction(e -> dialog.close());
        VBox body = new VBox(10 , boldLabel("Khách hàng :"),cbKh,lblLoi);
        btnOk.setOnAction(e -> {

            KhachHang kh = cbKh.getValue();

            if (kh  == null) { lblLoi.setText("⚠ Vui lòng chọn khách hàng!"); return; }
            try{
                PhienSuDung phien = phienBUS.moPhienMoi(kh.getMakh(),selectedMay.getMamay());
                dialog.close();
                ThongBaoDialogHelper.showSuccess(vboxKhuMay.getScene(),
                        "✔ Đã mở phiên " + phien.getMaPhien()
                                + "\nKhách: " + kh.getHo() + " " + kh.getTen()
                                + "  |  Máy: " + selectedMay.getTenmay());
                loadSoDo();

            } catch (Exception ex) {
                lblLoi.setText("⚠ " + ex.getMessage());
            }

        });
        dialog.setScene(new Scene(buildRoot("▶  Mở Phiên Mới", "#1565C0", body, btnOk, btnHuy)));
        dialog.showAndWait();

    }
    private VBox buildRoot(String title, String color, VBox body, Button btnOk, Button btnHuy) {
        VBox root = new VBox(14);
        root.setPadding(new Insets(24));
        root.setPrefWidth(500);
        root.setStyle("-fx-background-color:white; -fx-border-color:#E0E0E0; "
                + "-fx-border-width:1; -fx-border-radius:8; -fx-background-radius:8;");
        Label lbl = new Label(title);
        lbl.setStyle("-fx-font-size:18px; -fx-font-weight:bold; -fx-text-fill:" + color + ";");
        HBox btnRow = new HBox(10, btnOk, btnHuy);
        btnRow.setAlignment(Pos.CENTER_RIGHT);
        root.getChildren().addAll(lbl, new Separator(), body, new Separator(), btnRow);
        return root;
    }
    private Stage makeDialog(){
       //không có tiêu đề , không có nút đống (X) , không có thủ nhỏ (_)
        Stage s = new Stage(StageStyle.UNDECORATED);
        //không thể tương tác với model khác
        s.initModality(Modality.APPLICATION_MODAL);
        //ghi đè lên đối tượng cha
        s.initOwner(vboxKhuMay.getScene().getWindow());
        return s;
    }
    private Button secondaryBtn() {
        Button b = new Button("✖  Hủy");
        b.setStyle("-fx-background-color:#E0E0E0; -fx-pref-height:36px; "
                + "-fx-pref-width:90px; -fx-background-radius:6;");
        return b;
    }
    private Button primaryBtn(String text, String color) {
        Button b = new Button(text);
        b.setStyle("-fx-background-color:" + color + "; -fx-text-fill:white; "
                + "-fx-font-weight:bold; -fx-pref-height:36px; -fx-pref-width:190px; -fx-background-radius:6;");
        return b;
    }
    private Label errLabel() {
        Label l = new Label("");
        l.setStyle("-fx-text-fill:#C62828; -fx-font-size:12px; -fx-wrap-text:true;");
        l.setMaxWidth(Double.MAX_VALUE);
        return l;
    }
    private Label boldLabel(String text) {
        Label l = new Label(text);
        l.setStyle("-fx-font-weight:bold; -fx-font-size:13px;");
        return l;
    }
    private  ListCell<KhachHang> khachHangCell(){
        return new ListCell <>(){
          @Override  protected void updateItem(KhachHang kh, boolean empty){
              super.updateItem(kh,empty);
              if(empty || kh == null ){
                  setText(null);
                  return;
              }

              setText(String.format("%s  |  %s %s   | %, .0f đ",
                      kh.getMakh(),kh.getHo(),kh.getTen(),kh.getSodu()));
          }
        };
    }

    @FXML
    public void handleKetThucPhien() {
        if (selectedMay == null) return;
        try {
            entity.PhienSuDung phien = phienBUS.getPhienDangChoiByMay(selectedMay.getMamay());
            if (phien == null) {
                javafx.scene.control.Alert a = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("Máy " + selectedMay.getTenmay() + " không có phiên đang chơi.");
                a.showAndWait(); return;
            }
            phienBUS.ketThucPhien(phien.getMaPhien());
            selectedMay = null;
            btnMoPhien.setDisable(true);
            btnKetThuc.setDisable(true);
            lblSelectedMay.setText("Chưa chọn máy");
            loadSoDo();
        } catch (Exception e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Lỗi kết thúc phiên: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void startAutoRefresh() {
        autoRefresh = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            refreshCountdown--;
            lblAutoRefresh.setText("Tự động cập nhật: " + refreshCountdown + "s");
            if (refreshCountdown <= 0) {
                refreshCountdown = 30;
                loadSoDo();
            }
        }));
        autoRefresh.setCycleCount(Timeline.INDEFINITE);
        autoRefresh.play();
    }
}
