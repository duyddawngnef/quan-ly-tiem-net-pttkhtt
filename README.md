<div align="center">

```
 ██████╗ ██╗   ██╗ █████╗ ███╗   ██╗    ██╗  ██╗   ██╗    ████████╗██╗███████╗███╗   ███╗    ███╗   ██╗███████╗████████╗
██╔═══██╗██║   ██║██╔══██╗████╗  ██║    ██║  ╚██╗ ██╔╝    ╚══██╔══╝██║██╔════╝████╗ ████║    ████╗  ██║██╔════╝╚══██╔══╝
██║   ██║██║   ██║███████║██╔██╗ ██║    ██║   ╚████╔╝        ██║   ██║█████╗  ██╔████╔██║    ██╔██╗ ██║█████╗     ██║   
██║▄▄ ██║██║   ██║██╔══██║██║╚██╗██║    ██║    ╚██╔╝         ██║   ██║██╔══╝  ██║╚██╔╝██║    ██║╚██╗██║██╔══╝     ██║   
╚██████╔╝╚██████╔╝██║  ██║██║ ╚████║    ███████╗██║          ██║   ██║███████╗██║ ╚═╝ ██║    ██║ ╚████║███████╗   ██║   
 ╚══▀▀═╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚══════╝╚═╝          ╚═╝   ╚═╝╚══════╝╚═╝     ╚═╝    ╚═╝  ╚═══╝╚══════╝   ╚═╝   
```

# 🖥️ Quản Lý Tiệm Net

**Hệ thống quản lý quán Internet toàn diện được xây dựng bằng JavaFX & MySQL**

---

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-0078D7?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![MySQL](https://img.shields.io/badge/MySQL-9.5-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)]()

</div>

---

## 📋 Mục Lục

<details open>
<summary><b>Xem nội dung</b></summary>

- [🌟 Giới Thiệu](#-giới-thiệu)
- [✨ Tính Năng Nổi Bật](#-tính-năng-nổi-bật)
- [🛠️ Công Nghệ](#️-công-nghệ)
- [💻 Yêu Cầu Hệ Thống](#-yêu-cầu-hệ-thống)
- [🚀 Cài Đặt & Chạy](#-cài-đặt--chạy)
- [🗄️ Cơ Sở Dữ Liệu](#️-cơ-sở-dữ-liệu)
- [🏗️ Kiến Trúc Dự Án](#️-kiến-trúc-dự-án)
- [🔐 Phân Quyền](#-phân-quyền)
- [📊 Dữ Liệu Mẫu](#-dữ-liệu-mẫu)

</details>

---

## 🌟 Giới Thiệu

> **Quản Lý Tiệm Net** là ứng dụng desktop phục vụ quản lý quán Internet, được phát triển trong khuôn khổ môn học **Phân Tích Thiết Kế Hệ Thống Thông Tin (PTTKHTT)**.

Hệ thống hỗ trợ toàn bộ vòng đời hoạt động của một quán net, từ lúc khách ngồi vào máy đến khi thanh toán và rời đi:

```
┌─────────────────────────────────────────────────────────────────────┐
│                    LUỒNG HOẠT ĐỘNG TIỆM NET                        │
│                                                                     │
│  [Khách đến] ──► [Mở phiên] ──► [Chơi / Đặt dịch vụ]              │
│                                          │                           │
│  [Xuất PDF] ◄── [Trừ số dư] ◄── [Kết thúc phiên]                   │
└─────────────────────────────────────────────────────────────────────┘
```

---

## ✨ Tính Năng Nổi Bật

<table>
<tr>
<td width="50%">

### 👔 Quản Lý (Admin)
- 🗺️ **Sơ đồ máy** – Xem trực quan các khu và trạng thái máy
- 🖥️ **Quản lý máy tính** – Thêm, sửa, phân khu
- 👥 **Quản lý nhân viên** – CRUD + phân quyền chức vụ
- 🛒 **Quản lý dịch vụ** – Thực đơn + tồn kho tự động
- 📦 **Gói dịch vụ** – Combo Sáng, Đêm, Ngày, Tuần, Tháng
- 🎁 **Khuyến mãi** – Áp dụng theo %, số tiền hoặc tặng giờ
- 📥 **Nhập hàng** – Phiếu nhập từ nhà cung cấp
- 📈 **Thống kê & xuất Excel** – Báo cáo doanh thu

</td>
<td width="50%">

### 🧑‍💼 Thu Ngân / Nhân Viên
- 🖱️ **Mở / kết thúc phiên** – Trực tiếp từ sơ đồ máy
- 🥤 **Đặt dịch vụ** – Ghi nhận trong phiên
- 💳 **Nạp tiền** – Áp khuyến mãi tự động
- 🧾 **Hóa đơn** – Xem chi tiết & xuất **PDF**

### 👤 Khách Hàng
- 📋 **Thông tin tài khoản** – Số dư, lịch sử
- 🎮 **Phiên của tôi** – Lịch sử các phiên chơi
- 📦 **Gói của tôi** – Giờ còn lại trong gói
- 🛒 **Đặt dịch vụ** – Từ giao diện khách hàng

</td>
</tr>
</table>

---

## 🛠️ Công Nghệ

<div align="center">

| Thành Phần | Công Nghệ | Phiên Bản |
|:---:|:---:|:---:|
| 🔵 Ngôn ngữ | **Java** | `21` |
| 🎨 Giao diện | **JavaFX + FXML** | `21` |
| 🗃️ Cơ sở dữ liệu | **MySQL** | `9.5` |
| 🔌 JDBC Driver | **MySQL Connector/J** | `8.3.0` |
| 🏗️ Build Tool | **Apache Maven** | `3.x` |
| 📄 Xuất PDF | **iText PDF** | `5.5.13.3` |
| 📊 Xuất Excel | **Apache POI** | `5.5.1` |
| 🎭 Icon | **Ikonli Material Design 2** | `12.3.1` |

</div>

---

## 💻 Yêu Cầu Hệ Thống

```
✅ JDK 21+          ✅ MySQL 8.0 / 9.x         ✅ Maven 3.6+
✅ RAM ≥ 512 MB     ✅ Windows 10/11 / Linux / macOS
```

---

## 🚀 Cài Đặt & Chạy

### 1️⃣ Clone dự án

```bash
git clone https://github.com/duyddawngnef/quan-ly-tiem-net-pttkhtt.git
cd quan-ly-tiem-net-pttkhtt
```

### 2️⃣ Tạo cơ sở dữ liệu

```bash
# Chạy script SQL để tạo DB + bảng + dữ liệu mẫu
mysql -u root -p < database/quanlytiemnet.sql
```

> 💡 Hoặc mở `database/quanlytiemnet.sql` bằng **HeidiSQL** / **MySQL Workbench** > Execute.

### 3️⃣ Cấu hình kết nối

Mở `src/main/resources/config.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/quanlytiemnet_simple?useUnicode=true&characterEncoding=utf8
db.username=root
db.password=YOUR_PASSWORD_HERE
```

### 4️⃣ Chạy ứng dụng

```bash
mvn clean javafx:run
```

---

## 🗄️ Cơ Sở Dữ Liệu

**Database:** `quanlytiemnet_simple` · **Charset:** `utf8mb4` · **Engine:** InnoDB

<details>
<summary><b>📐 Sơ đồ quan hệ các bảng (ERD)</b></summary>

```
            ┌──────────┐        ┌─────────────────────┐
            │ nhanvien │        │      khachhang       │
            └────┬─────┘        └──────┬───────────────┘
                 │                     │
     ┌───────────┼─────────────────────┤
     │      ┌────▼──────────────────┐  │
     │      │     phiensudung       │  │
     │      │  (MaPhien, MaKH,      │  │
     │      │   MaMay, MaNV, ...)   │  │
     │      └────┬──────────────────┘  │
     │           │                     │
     │    ┌──────┴──────┐    ┌─────────▼──────────┐
     │    │sudungdichvu │    │  lichsunaptien      │
     │    └──────┬──────┘    │  + chuongtrinhkm    │
     │           │           └────────────────────┘
     │      ┌────▼───┐
     │      │ dichvu │◄──────── chitietphieunhap
     │      └────────┘               │
     │                        phieunhaphang ──► nhacungcap
     │    ┌──────────────────────────────────┐
     │    │           hoadon                 │
     │    │  (MaHD, MaPhien, MaKH, MaNV,...) │
     │    └──────────────┬───────────────────┘
     │                   │
     │            chitiethoadon
     │         (GIOCHOI / DICHVU)
     │
     │    ┌────────────────────────────┐
     └───►│  goidichvu_khachhang       │◄── goidichvu
          └────────────────────────────┘

┌──────────┐     ┌─────────┐
│  khumay  │◄────│ maytinh │
└──────────┘     └─────────┘
```

</details>

<details>
<summary><b>📋 Danh sách 16 bảng</b></summary>

| # | Bảng | Mô tả |
|---|---|---|
| 1 | `nhanvien` | Nhân viên (Quản lý / Thu ngân / Nhân viên) |
| 2 | `khachhang` | Khách hàng & số dư tài khoản |
| 3 | `maytinh` | Danh sách máy tính |
| 4 | `khumay` | Phân khu & giá mỗi giờ |
| 5 | `phiensudung` | Phiên sử dụng máy |
| 6 | `dichvu` | Danh mục dịch vụ (đồ uống, thức ăn...) |
| 7 | `sudungdichvu` | Dịch vụ đặt trong mỗi phiên |
| 8 | `goidichvu` | Gói giờ chơi |
| 9 | `goidichvu_khachhang` | Gói của từng khách hàng |
| 10 | `hoadon` | Hóa đơn thanh toán |
| 11 | `chitiethoadon` | Chi tiết hóa đơn |
| 12 | `lichsunaptien` | Lịch sử nạp tiền |
| 13 | `chuongtrinhkhuyenmai` | Chương trình khuyến mãi |
| 14 | `nhacungcap` | Nhà cung cấp hàng hóa |
| 15 | `phieunhaphang` | Phiếu nhập hàng |
| 16 | `chitietphieunhap` | Chi tiết phiếu nhập hàng |

</details>

---

## 🏗️ Kiến Trúc Dự Án

Dự án tuân theo mô hình **3-Tier Architecture**:

```
┌─────────────────────────────────────────────────────────┐
│                  PRESENTATION TIER                      │
│   JavaFX (FXML + CSS) · Controller · Dialog · Utils     │
├─────────────────────────────────────────────────────────┤
│                  BUSINESS LOGIC TIER                    │
│           BUS (Business Logic / Validation)             │
├─────────────────────────────────────────────────────────┤
│                    DATA ACCESS TIER                     │
│          DAO (SQL Queries) · Entity (POJO)              │
│              DBConnection · MySQL 9.x                   │
└─────────────────────────────────────────────────────────┘
```

```
quan-ly-tiem-net-pttkhtt/
│
├── 📂 database/
│   └── 🗃️ quanlytiemnet.sql         # Script CSDL + dữ liệu mẫu
│
├── 📂 docs/
│   └── 📘 BaoCao_PTTKHTT.docx       # Tài liệu phân tích nghiệp vụ
│
├── 📂 src/main/
│   ├── 📂 java/
│   │   ├── 🚀 Main.java
│   │   ├── 📂 entity/     ← 17 POJO classes
│   │   ├── 📂 dao/        ← 18 DAO classes (SQL)
│   │   ├── 📂 bus/        ← 15 BUS classes (Logic)
│   │   ├── 📂 gui/
│   │   │   ├── 📂 controller/  ← 22 Controllers
│   │   │   └── 📂 dialog/
│   │   └── 📂 utils/      ← PDF · Excel · Session · Permission
│   │
│   └── 📂 resources/
│       ├── ⚙️ config.properties
│       ├── 📂 fxml/       ← 22 FXML screens
│       ├── 📂 css/        ← Stylesheet
│       ├── 📂 fonts/      ← Material Design Icons
│       └── 📂 images/
│
└── 📄 pom.xml
```

### 🔧 Lớp Tiện Ích (Utils)

| Lớp | Chức năng |
|---|---|
| `SessionManager` | Quản lý phiên đăng nhập hiện tại |
| `PermissionHelper` | Kiểm soát quyền truy cập theo vai trò |
| `HoaDonExporter` | Xuất hóa đơn → **PDF** (iText) |
| `ThongKeExcelExporter` | Xuất báo cáo → **Excel** (Apache POI) |
| `PasswordEncoder` | Mã hóa mật khẩu **SHA-256** |
| `DBConnection` | Singleton quản lý kết nối MySQL |

---

## 🔐 Phân Quyền

```
┌────────────────────────────────────────────────────────────────┐
│  Role: QUANLY (Quản lý)           ← Đăng nhập = NV account   │
│  ✅ Tất cả tính năng                                           │
│  ✅ Thống kê & báo cáo                                         │
│  ✅ Quản lý nhân viên & cấu hình                               │
├────────────────────────────────────────────────────────────────┤
│  Role: THUNGAN / NHANVIEN         ← Đăng nhập = NV account   │
│  ✅ Mở/Kết thúc phiên                                          │
│  ✅ Nạp tiền, bán gói, đặt dịch vụ                            │
│  ✅ Hóa đơn, khách hàng                                        │
│  ❌ Thống kê, cấu hình hệ thống                                │
├────────────────────────────────────────────────────────────────┤
│  Role: KHACHHANG                  ← Đăng nhập = KH account   │
│  ✅ Xem thông tin & lịch sử cá nhân                           │
│  ✅ Đặt dịch vụ trong phiên hiện tại                          │
│  ❌ Mọi chức năng quản trị                                     │
└────────────────────────────────────────────────────────────────┘
```

---

## 📊 Dữ Liệu Mẫu

### 🔑 Tài Khoản Nhân Viên

| Tên Đăng Nhập | Mật Khẩu | Vai Trò |
|:---:|:---:|:---:|
| `admin` | `123456` | 👔 Quản lý |
| `thungan01` | `123456` | 💰 Thu ngân |
| `thungan02` | `123456` | 💰 Thu ngân |
| `kythuat01` | `123456` | 🔧 Nhân viên |
| `phucvu02` | `123456` | 🧹 Nhân viên |

### 👤 Tài Khoản Khách Hàng (mẫu)

| Tên Đăng Nhập | Mật Khẩu | Số Dư |
|:---:|:---:|:---:|
| `hoangnam` | `123456` | 83,800 đ |
| `hongle` | `123456` | 168,000 đ |
| `hieugaming` | `123456` | 630,000 đ |
| `long_dragon` | `123456` | 160,000 đ |

### 🎮 Các Khu Máy

| Khu | Tên | Giá/Giờ | Số Máy |
|:---:|---|:---:|:---:|
| 🟩 KHU001 | Khu Thường (A) | 5,000 đ | 20 |
| 🟦 KHU002 | Khu VIP (B) | 8,000 đ | 15 |
| 🟥 KHU003 | Khu Thi Đấu (S) | 10,000 đ | 10 |
| 🟪 KHU004 | Khu Couple (C) | 12,000 đ | 5 |
| 🟨 KHU005 | Khu Hút Thuốc (D) | 6,000 đ | 15 |

### 📦 Gói Dịch Vụ

| Gói | Tên | Số Giờ | Giá Gói |
|:---:|---|:---:|:---:|
| GOI001 | ☀️ Combo Sáng (3h) | 3h | 12,000 đ |
| GOI002 | 🌙 Combo Đêm (7h) | 7h | 25,000 đ |
| GOI003 | 📅 Gói Ngày (10h) | 10h | 40,000 đ |
| GOI004 | 📆 Gói Tuần (50h) | 50h | 200,000 đ |
| GOI005 | 👑 Gói VIP Tháng | 80h | 500,000 đ |

---

<div align="center">

## 💳 Hình Thức Thanh Toán Hỗ Trợ

![TienMat](https://img.shields.io/badge/Tiền_Mặt-💵-4CAF50?style=flat-square)
![TaiKhoan](https://img.shields.io/badge/Tài_Khoản-💳-2196F3?style=flat-square)
![CKhoan](https://img.shields.io/badge/Chuyển_Khoản-🏦-FF9800?style=flat-square)
![MoMo](https://img.shields.io/badge/MoMo-💜-E91E8C?style=flat-square)
![VNPay](https://img.shields.io/badge/VNPay-🔴-D32F2F?style=flat-square)
![ZaloPay](https://img.shields.io/badge/ZaloPay-🔵-1565C0?style=flat-square)

---

**Môn học:** Phân Tích Thiết Kế Hệ Thống Thông Tin · **Phiên bản:** 1.0-SNAPSHOT

[![MIT License](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

</div>
